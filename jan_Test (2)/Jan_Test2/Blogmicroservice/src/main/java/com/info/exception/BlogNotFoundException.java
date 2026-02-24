package com.info.exception;

public class BlogNotFoundException extends RuntimeException {
  public BlogNotFoundException(String msg) {
	  super(msg);
  }
}
