package io.probono.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.probono.dto.ActivistDTO;
import io.probono.dto.ProbonoProjectDTO;
import io.probono.entity.Activist;
import io.probono.entity.ProbonoProject;
import io.probono.service.ProbonoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("rest")
public class ProbonoRestController {

	@Autowired
	ProbonoService probonoService;

	// 모두 ProbonoProject 검색 메소드
	@ApiOperation(value = "모든 probono_project 데이터 가져 오기", notes = " MySQL probono_project table에서 전체 데이터 리스트 형식으로 받아와 DTO로 변환하여 리턴.")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 500, message = "500 에러 발생 시 출력 메세지, Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생 시 출력 메세지, Not Found !"),
			@ApiResponse(code = 402, message = "402 에러 발생 시 출력 메세지, I don't know !")})
	@GetMapping("/probonoProjectAll")
	public List<ProbonoProjectDTO> probonoProjectAll() throws Exception {

		return probonoService.getProbonoProjectList();
	}
	
	@ApiOperation(value = "Activist PK값으로 해당 Activst 데이터 가져 오기", notes = " MySQL activist table에서 id(PK)로 검색하여 데이터 리턴.")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 500, message = "500 에러 발생 시 출력 메세지, Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생 시 출력 메세지, Not Found !"),
			@ApiResponse(code = 402, message = "402 에러 발생 시 출력 메세지, I don't know !")})
	@GetMapping("/getActivist")
	public ActivistDTO getActivist(@ApiParam(value = "activistId", required = true, example = "giver1") @RequestParam("id")String activistId ) throws Exception {

		return probonoService.getActivist(activistId);
	}
	
	@ApiOperation(value = "모든 activist 데이터 가져오기", notes = " MySQL activist table에서 전체 데이터 리스트 형식으로 받아와 DTO로 변환 후 리턴.")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 500, message = "500 에러 발생 시 출력 메세지, Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생 시 출력 메세지, Not Found !"),
			@ApiResponse(code = 402, message = "402 에러 발생 시 출력 메세지, I don't know !")})
	@GetMapping("/activistAll")
	public List<ActivistDTO> activistAll() throws Exception {

		return probonoService.getActivistList();
	}
	
	@ApiOperation(value = "probono_project table에 새로운 데이터 추가하기", notes = " MySQL probono_project table에 DTO파라미터를 Entity로 변환 후 데이터에 입력, 결과에 따라 true 또는 false를 반환한다.")
	@ApiResponses({ @ApiResponse(code = 200, message = "success"),
			@ApiResponse(code = 500, message = "500 에러 발생 시 출력 메세지, Internal Server Error !"),
			@ApiResponse(code = 404, message = "404 에러 발생 시 출력 메세지, Not Found !"),
			@ApiResponse(code = 402, message = "402 에러 발생 시 출력 메세지, I don't know !")})
	@PostMapping("/probonoProjectInsert")
	public boolean probonoProjectInsert(ProbonoProjectDTO probonoProject) throws Exception {
		//@ApiParam(value = "probonoProjectName/projectContent/activistId/probonoId/receiveId", required = false, example = "키다리 아저씨 프로젝트/장학금후원/giver1/schweitzer/receivePeople1")

		if (probonoProject.getProbonoProjectName() != null && probonoProject.getProbonoId() != null
				&& probonoProject.getReceiveId() != null && probonoProject.getProjectContent() != null) {
			
			probonoService.insertProbonoProject(probonoProject);
			return true;
			}
		return false;
	}

}
