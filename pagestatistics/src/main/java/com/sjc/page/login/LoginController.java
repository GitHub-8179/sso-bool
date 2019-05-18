package com.sjc.page.login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/login")
@Api(value="/login", tags="用户登录模块")
public class LoginController {

//@ApiOperation(value = "dicValues", notes = "keys-values", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//@ApiOperation(value = "查询参与人信息", notes = "查询数据库中某个的学生信息")
//@ApiImplicitParam(name = "jionPersio", value = "参与人信息条件", paramType = "path", required = true, dataType = "JionPersion")
//@ApiResponses({
//    @ApiResponse(code=400,message = "请求参数没有填好"),
//	 @ApiResponse(code = 200, message = "Suceess|OK"),
//     @ApiResponse(code = 401, message = "not authorized!"), 
//     @ApiResponse(code = 403, message = "forbidden!!!"),
//    @ApiResponse(code=404,message="请求路径没有找到")
//})

	@ApiOperation(value = "用户登录校验", notes = "登录")
	@PostMapping(value="/loginVerify")
	public ResponseEntity addInfo(HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
	
		try {
				
			return new ResponseEntity("this is test OK!",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity("this is test error!",HttpStatus.INTERNAL_SERVER_ERROR);
	
	}
	
}
