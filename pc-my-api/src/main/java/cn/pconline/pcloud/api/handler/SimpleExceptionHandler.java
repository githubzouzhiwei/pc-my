/*
package cn.pconline.pcloud.api.handler;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import cn.pconline.framework.enums.ResponseCodeEnum;
import cn.pconline.framework.exception.AccessForbiddenException;
import cn.pconline.framework.exception.EmpytCollectionException;
import cn.pconline.framework.exception.FieldValidateException;
import cn.pconline.framework.exception.ObjectNotFoundException;
import cn.pconline.framework.util.ResponseResult;

@ControllerAdvice(annotations = { RestController.class })
public class SimpleExceptionHandler {

	public static final Logger logger = Logger.getLogger(SimpleExceptionHandler.class);

	// 通用异常的处理，返回500
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public ResponseResult<?> handleException(HttpServletRequest request, Exception e) {

		logger.error(e.getMessage(), e);

		if (e instanceof org.springframework.web.servlet.NoHandlerFoundException) {
			return ResponseResult.build(ResponseCodeEnum.SC_NOT_FOUND, e.getMessage());
		} else {
			return ResponseResult.build(ResponseCodeEnum.ERROR, e.getMessage());
		}
	}

	// 参数不合法
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value = { ConstraintViolationException.class, FieldValidateException.class })
	@ResponseBody
	public ResponseResult<?> constraintViolationExceptionHandler(HttpServletRequest request, Exception e)
			throws Exception {

		logger.error(e.getMessage(), e);

		if (e instanceof FieldValidateException) {
			return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, ((FieldValidateException) e).getFieldMsgs());
		}
		return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, e.getMessage());
	}

	// 空结果集
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ExceptionHandler(value = { ObjectNotFoundException.class, EmptyResultDataAccessException.class,
			EmpytCollectionException.class })
	@ResponseBody
	public ResponseResult<?> notFoundExceptionHandler(HttpServletRequest request, Exception e) throws Exception {

		logger.error(e.getMessage(), e);

		return ResponseResult.build(ResponseCodeEnum.SC_NOT_FOUND, e.getMessage());
	}

	// 禁止访问
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	@ExceptionHandler(value = { AccessForbiddenException.class })
	@ResponseBody
	public ResponseResult<?> forbiddenExceptionHandler(HttpServletRequest request, Exception e) throws Exception {

		logger.error(e.getMessage(), e);

		return ResponseResult.build(ResponseCodeEnum.BAD_REQUEST, e.getMessage());
	}

}
*/
