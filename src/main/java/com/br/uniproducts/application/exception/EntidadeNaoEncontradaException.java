package com.br.uniproducts.application.exception;

public abstract class EntidadeNaoEncontradaException extends RuntimeException
{
	public EntidadeNaoEncontradaException(String message)
	{
		super(message);
	}
}
