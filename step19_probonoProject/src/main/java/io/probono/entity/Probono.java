package io.probono.entity;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Probono {
	
	@Id
	@Column(name="probono_id")
	private String probonoId;
	
	@Column(name="probono_name")
	private String probonoName;
	
	@Column(name="probono_purpose")
	private String probonoPurpose;
	
	@OneToMany(mappedBy="probonoId")
	private List<ProbonoProject> ProbonoProjects;
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("프로보노 정보 1. 프로보노 아이디 = ");
		builder.append(probonoId);
		builder.append("2. 프로보노 이름 : ");
		builder.append(probonoName);
		builder.append("3. 프로보노 목적 : ");
		builder.append(probonoPurpose);
		return builder.toString();
	}
}
