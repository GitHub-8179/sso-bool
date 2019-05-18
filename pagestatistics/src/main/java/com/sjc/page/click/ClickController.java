package com.sjc.page.click;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
//@Controller
@RequestMapping("/click")
@Api(value="/click1", tags="点击事件记录模块")
public class ClickController {

////	@ApiOperation(value = "dicValues", notes = "keys-values", httpMethod = "POST", produces = MediaType.APPLICATION_JSON_VALUE)
//	@ApiOperation(value = "查询参与人信息", notes = "查询数据库中某个的学生信息")
////	@ApiImplicitParam(name = "jionPersio", value = "参与人信息条件", paramType = "path", required = true, dataType = "JionPersion")
//	@ApiResponses({
//        @ApiResponse(code=400,message = "请求参数没有填好"),
//		 @ApiResponse(code = 200, message = "Suceess|OK"),
//	     @ApiResponse(code = 401, message = "not authorized!"), 
//	     @ApiResponse(code = 403, message = "forbidden!!!"),
//        @ApiResponse(code=404,message="请求路径没有找到")
//	})
//
////  	@RequestMapping(value="/addInfo", method = RequestMethod.POST)
//	@GetMapping(value="/addInfo")
//	@ResponseBody
//	public ResponseEntity addInfo(HttpServletRequest request, HttpServletResponse response
//			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {
//
//  		try {
//			return new ResponseEntity("this is test!",HttpStatus.OK);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return new ResponseEntity("this is test!",HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
	
	@ApiOperation(value = "查询参与人信息", notes = "查询数据库中某个的学生信息")
	@ApiResponses({
        @ApiResponse(code=400,message = "请求参数没有填好"),
		 @ApiResponse(code = 200, message = "Suceess|OK"),
	     @ApiResponse(code = 401, message = "not authorized!"), 
	     @ApiResponse(code = 403, message = "forbidden!!!"),
        @ApiResponse(code=404,message="请求路径没有找到")
	})

	@GetMapping(value="/addInfo1")
	@ResponseBody
	public Map addInfo1(HttpServletRequest request, HttpServletResponse response
			,@RequestParam(value = "page", defaultValue = "1") Integer page,@RequestParam(value = "rows", defaultValue = "10") Integer rows) {

		return new HashMap();

	}
}
