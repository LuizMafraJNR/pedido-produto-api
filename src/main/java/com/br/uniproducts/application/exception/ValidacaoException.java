package com.br.uniproducts.application.exception;

import org.springframework.validation.BindingResult;

public class ValidacaoException extends RuntimeException
{
	private BindingResult bindingResult;
	public ValidacaoException(String message)
	{
		super(message);
	}

	public ValidacaoException(BindingResult bindingResult)
	{
		this.bindingResult = bindingResult;
	}

	public ValidacaoException(String message, BindingResult bindingResult)
	{
		super(message);
		this.bindingResult = bindingResult;
	}

	public ValidacaoException(String message, Throwable cause, BindingResult bindingResult)
	{
		super(message, cause);
		this.bindingResult = bindingResult;
	}

	public ValidacaoException(Throwable cause, BindingResult bindingResult)
	{
		super(cause);
		this.bindingResult = bindingResult;
	}

	public ValidacaoException(String message, Throwable cause, boolean enableSuppression,
		boolean writableStackTrace, BindingResult bindingResult)
	{
		super(message, cause, enableSuppression, writableStackTrace);
		this.bindingResult = bindingResult;
	}

	public BindingResult getBindingResult()
	{
		return bindingResult;
	}
}
