package com.petease.app.exception;

public class RecordNotExistException extends Exception
{
	private static final long serialVersionUID = 1L;

	public RecordNotExistException(String msg)
	{
		super(msg);
	}
}
