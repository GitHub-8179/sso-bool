package priv.sjc.base.config;

import java.util.List;
import java.util.Set;

import javax.jws.WebResult;
import javax.security.auth.message.AuthException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕捉
 **/
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());
    
    
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public  ResponseEntity resolveConstraintViolationException(ConstraintViolationException ex){
    	Set<ConstraintViolation<?>>  errors= ex.getConstraintViolations();
        String errorMessage = "";
        if(!errors.isEmpty()) {
        	StringBuilder msgBuilder = new StringBuilder();
        	for (ConstraintViolation objectError : errors) {
                msgBuilder.append(objectError.getMessage()).append(",");
            }
	    	  errorMessage = msgBuilder.toString();
	          if (errorMessage.length() > 1) {
	              errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
	          }
        }
    	
        return new ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity resolveMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<ObjectError> errors =ex.getBindingResult().getAllErrors();
        String errorMessage = "";
        if(!errors.isEmpty()) {
        	StringBuilder msgBuilder = new StringBuilder();
        	for (ObjectError objectError : errors) {
                msgBuilder.append(objectError.getDefaultMessage()).append(",");
            }
	    	  errorMessage = msgBuilder.toString();
	          if (errorMessage.length() > 1) {
	              errorMessage = errorMessage.substring(0, errorMessage.length() - 1);
	          }
        }
        

        return new ResponseEntity(errorMessage,HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * 全局异常
     * @param req
     * @param e
     * @return
     * @throws Exception
     */
//    @ExceptionHandler(value = Exception.class)
//    @ResponseBody
//    public ResponseEntity handleException(HttpServletRequest req, Exception e) throws Exception {
//        return new ResponseEntity("程序异常，请联系管理员！",HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}