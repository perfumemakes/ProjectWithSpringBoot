package pdt.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional
@Entity
public class Keyword {
	
	@Id
	private String keyword1;

	private int count;

}
