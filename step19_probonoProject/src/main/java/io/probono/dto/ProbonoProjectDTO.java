/*
CREATE TABLE probono_project (
	   probono_project_id     		NUMBER(5) PRIMARY KEY,
	   probono_project_name 		VARCHAR2(50) NOT NULL,
       probono_id           			VARCHAR2(50) NOT NULL,       
       activist_id          				VARCHAR2(20) NOT NULL,
       receive_id           				VARCHAR2(20) NOT NULL, 
       project_content      			VARCHAR2(100) NOT NULL
);   */

package io.probono.dto;

import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProbonoProjectDTO {
	@ApiParam(value = "probonoProjectId (PK, auto-increment)", required = true, example = "1")
	private int probonoProjectId;
	@ApiParam(value = "probonoProjectName", required = false, example = "키다리아저씨 프로젝트")
	private String probonoProjectName;
	@ApiParam(value = "probonoId", required = false, example = "schweitzer")
	private String probonoId;
	@ApiParam(value = "activistId", required = false, example = "giver1")
	private String activistId;
	@ApiParam(value = "receiveId", required = false, example = "receivePeople1")
	private String receiveId;
	@ApiParam(value = "projectContent", required = false, example ="장학금후원")
	private String projectContent;

	public ProbonoProjectDTO(String probonoProjectName, String probonoId, String activistId, String receiveId,
			String projectContent) {
		
		this.probonoProjectName = probonoProjectName;
		this.probonoId = probonoId;
		this.activistId = activistId;
		this.receiveId = receiveId;
		this.projectContent = projectContent;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("1. 프로보노 아이디: ");
		builder.append(probonoProjectId);
		builder.append("2. 프로보노 프로젝트명 : ");
		builder.append(probonoProjectName);
		builder.append("3. 프로보노 정보 : ");
		builder.append(probonoId);
		builder.append("4. 재능 기부자 정보 : ");
		builder.append(activistId);
		builder.append("5. 수해자 정보 : ");
		builder.append(receiveId);
		builder.append("6. 프로젝트 제공내용 : ");
		builder.append(projectContent);
		return builder.toString();
	}
	

}
