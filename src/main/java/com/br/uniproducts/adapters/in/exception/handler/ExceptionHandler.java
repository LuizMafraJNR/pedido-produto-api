package com.br.uniproducts.adapters.in.exception.handler;

import com.br.uniproducts.application.exception.EntidadeEmUsoException;
import com.br.uniproducts.application.exception.EntidadeNaoEncontradaException;
import com.br.uniproducts.application.exception.NegocioException;
import com.br.uniproducts.application.exception.ValidacaoException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.IgnoredPropertyException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.beans.TypeMismatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler
{
	public static final String MSG_ERRO_GENERICO_USER_FINAL = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se o problema persistir, entre em contato com o administrador do sistema.";

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		ProblemType problemType = ProblemType.RECURSO_NAO_ENCONTRADO;
		String detail = String.format("O recurso %s, que você tentou acessar, é inexistente",
			ex.getRequestURL());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		return handleValidationInternal(ex, ex.getBindingResult(), headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
		HttpStatusCode status, WebRequest request)
	{
		if (ex instanceof MethodArgumentTypeMismatchException)
		{
			return handleMethodArgumentTypeMismatch((MethodArgumentTypeMismatchException) ex, headers,
				status, request);
		}

		return super.handleTypeMismatch(ex, headers, status, request);
	}

	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		Throwable rootCause = ex;
		while (rootCause.getCause() != null && rootCause.getCause() != rootCause)
		{
			rootCause = rootCause.getCause();
		}

		if (rootCause instanceof InvalidFormatException)
		{
			return handleInvalidFormat((InvalidFormatException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof IgnoredPropertyException)
		{
			return handlePropertyIgnored((IgnoredPropertyException) rootCause, headers, status, request);
		}
		else if (rootCause instanceof UnrecognizedPropertyException)
		{
			return handleJsonMapping((JsonMappingException) rootCause, status, request);
		}

		Problem problem = createProblemBuilder(status, ProblemType.MENSAGEM_INCOMPREENSIVEL,
			"O corpo da requisição está inválido. Verifique erro de sintaxe.").build();
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
		MethodArgumentTypeMismatchException ex, HttpHeaders headers, HttpStatusCode status,
		WebRequest request)
	{
		ProblemType problemType = ProblemType.PARAMETRO_INVALIDO;
		String detail = String.format(
			"O parâmetro de URL '%s' recebeu o valor '%s', que é de um tipo inválido. "
				+ "Corrija e informe um valor compatível com o tipo %s", ex.getName(), ex.getValue(),
			ex.getRequiredType().getSimpleName());
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request)
	{
		HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
		ProblemType problemType = ProblemType.ERRO_DO_SISTEMA;

		String detail = MSG_ERRO_GENERICO_USER_FINAL;
		ex.printStackTrace();
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handleJsonMapping(JsonMappingException rootCause,
		HttpStatusCode status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
			"A propriedade '%s' não existe. Corrija ou remova-a para continuar", path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(rootCause, problem, new HttpHeaders(), status, request);
	}

	private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		String path = joinPath(ex.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
			"A propriedade '%s' não existe. Corrija ou remova-a para continuar", path);
		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(
			MSG_ERRO_GENERICO_USER_FINAL).build();

		return handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handlePropertyIgnored(IgnoredPropertyException rootCause,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format("A propriedade '%s' não é suportada. Remova-a para continuar",
			path);
		Problem problem = createProblemBuilder(status, problemType, detail).build();

		return handleExceptionInternal(rootCause, problem, headers, status, request);
	}

	private String joinPath(List<JsonMappingException.Reference> path)
	{
		return path.stream().map(ref -> ref.getFieldName()).collect(Collectors.joining("."));
	}

	private ResponseEntity<Object> handleValidationInternal(Exception ex, BindingResult bindingResult,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";

		List<Problem.Object> problemObjects = bindingResult.getAllErrors().stream().map(objectError -> {
			String message = messageSource.getMessage(objectError, LocaleContextHolder.getLocale());
			String name = objectError.getObjectName();

			if (objectError instanceof FieldError)
			{
				name = ((FieldError) objectError).getField();
			}

			return Problem.Object.builder().name(name).userMessage(message).build();
		}).collect(Collectors.toList());

		Problem problem = createProblemBuilder(status, ProblemType.DADOS_INVALIDOS, detail).userMessage(
			detail).objects(problemObjects).build();

		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}

	private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException rootCause,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		String path = joinPath(rootCause.getPath());
		ProblemType problemType = ProblemType.MENSAGEM_INCOMPREENSIVEL;
		String detail = String.format(
			"A propriedade '%s' recebeu o valor '%s' que é de um tipo inválido."
				+ " Corrija e informe um valor compatível com o tipo %s", path, rootCause.getValue(),
			rootCause.getTargetType().getSimpleName());

		Problem problem = createProblemBuilder(status, problemType, detail).userMessage(
			MSG_ERRO_GENERICO_USER_FINAL).build();

		return handleExceptionInternal(rootCause, problem, headers, status, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(EntidadeNaoEncontradaException.class)
	public ResponseEntity<?> handleEstadoNaoEncontradoException(EntidadeNaoEncontradaException e,
		WebRequest request)
	{
		Problem problem = createProblemBuilder(HttpStatus.NOT_FOUND, ProblemType.RECURSO_NAO_ENCONTRADO,
			e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handleEntidadeEmUsoException(WebRequest request, EntidadeEmUsoException e)
	{
		Problem problem = createProblemBuilder(HttpStatus.CONFLICT, ProblemType.ENTIDADE_EM_USO,
			e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.CONFLICT, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(ValidacaoException.class)
	public ResponseEntity<Object> handleValidacaoException(ValidacaoException ex, WebRequest request)
	{
		return handleValidationInternal(ex, ex.getBindingResult(), new HttpHeaders(),
			HttpStatus.BAD_REQUEST, request);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handleNegocioException(NegocioException e, WebRequest request)
	{
		Problem problem = createProblemBuilder(HttpStatus.BAD_REQUEST, ProblemType.ERRO_NEGOCIO,
			e.getMessage()).build();
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}

	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
		HttpHeaders headers, HttpStatusCode status, WebRequest request)
	{
		if (Objects.isNull(body))
		{
			String reasonPharase = HttpStatus.valueOf(String.valueOf(status.value())).getReasonPhrase();
			body = Problem.builder().status(status.value()).title(reasonPharase)
				.dateTime(OffsetDateTime.now()).userMessage(MSG_ERRO_GENERICO_USER_FINAL).build();
		}
		else if (body instanceof String)
		{
			body = Problem.builder().status(status.value()).title((String) body)
				.dateTime(OffsetDateTime.now()).userMessage(MSG_ERRO_GENERICO_USER_FINAL).build();
		}
		return super.handleExceptionInternal(ex, body, headers, status, request);
	}

	private Problem.ProblemBuilder createProblemBuilder(HttpStatusCode cause, ProblemType problemType,
		String detail)
	{
		return Problem.builder().status(cause.value()).type(problemType.getUri())
			.title(problemType.getTitle()).detail(detail).dateTime(OffsetDateTime.now());
	}
}
