package io.probono.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.probono.dto.ActivistDTO;
import io.probono.dto.ProbonoProjectDTO;
import io.probono.dto.RecipientDTO;
import io.probono.entity.Activist;
import io.probono.entity.ProbonoProject;
import io.probono.entity.Recipient;
import io.probono.repository.ActivistRepository;
import io.probono.repository.ProbonoProjectRepository;
import io.probono.repository.ProbonoRepository;
import io.probono.repository.RecipientRepository;

@Service
public class ProbonoService {

	@Autowired   
	private ActivistRepository activistRepo;
	
	@Autowired   
	private RecipientRepository recipientRepo;
	
	@Autowired   
	private ProbonoRepository probonoRepo;

	@Autowired
	private ProbonoProjectRepository probonoProjectRepo;

	ModelMapper mapper = new ModelMapper();

	// Activist Table CRUD
	public List<ActivistDTO> getActivistList() {
		
		List<Activist> activistEntity =  (List<Activist>) activistRepo.findAll();
		List<ActivistDTO> activist = activistEntity.stream().map(entity -> mapper.map(entity, ActivistDTO.class)).collect(Collectors.toList());
		return activist;
	}

	public void insertActivist(ActivistDTO activist) {
		Activist activistEntity = mapper.map(activist, Activist.class);
		activistRepo.save(activistEntity);
	}

	public ActivistDTO getActivist(String id) {
		Activist activistEntity = activistRepo.findById(id).get();
		ActivistDTO activist = mapper.map(activistEntity, ActivistDTO.class);
		
		return activist;
	}

	public void updateActivist(String id, String major) {
		Activist findActivist = activistRepo.findById(id).get();
		findActivist.setMajor(major);
		activistRepo.save(findActivist);
	}

	public void deleteActivist(String id) {
		activistRepo.deleteById(id);
	}
	
	// Recipient Table CRUD
		public List<RecipientDTO> getRecipientList() {
			List<Recipient> recipientEntity = (List<Recipient>) recipientRepo.findAll();
			List<RecipientDTO> recipient = Arrays.asList(mapper.map(recipientEntity, RecipientDTO[].class));
			List<RecipientDTO> recipient2 = recipientEntity.stream().map(a -> mapper.map(a, RecipientDTO.class)).collect(Collectors.toList());
			return recipient2;
		}

		public void insertRecipient(RecipientDTO recipient) {
			Recipient recipientEntity = mapper.map(recipient, Recipient.class);
			recipientRepo.save(recipientEntity);
		}

		public Recipient getRecipient(String id) {
			return recipientRepo.findById(id).get();
		}
		
		public void updateRecipient(String id, String receiveHopeContent) {
			Recipient findRecipient = recipientRepo.findById(id).get();
			findRecipient.setReceiveHopeContent(receiveHopeContent);
			recipientRepo.save(findRecipient);
		}


		public void deleteRecipient(String id) {
			recipientRepo.deleteById(id);
		}

	// ProbonoProject CRUD
	public List<ProbonoProjectDTO> getProbonoProjectList() throws Exception {

		List<ProbonoProject> probonoProjectAll = (List<ProbonoProject>) probonoProjectRepo.findAll();

		if (probonoProjectAll == null) {
			throw new Exception();
		}

		List<ProbonoProjectDTO> probonoProjectDTOAll = Arrays
				.asList(mapper.map(probonoProjectAll, ProbonoProjectDTO[].class));

		return probonoProjectDTOAll;
	}

	public void insertProbonoProject(ProbonoProjectDTO probonoProject) {
		ProbonoProject probonoProjectEntity = mapper.map(probonoProject, ProbonoProject.class);
		
		probonoProjectRepo.save(probonoProjectEntity);
	}

	public ProbonoProject getProbonoProject(int probonoProjectId) {
		return probonoProjectRepo.findById(probonoProjectId).get();
	}

	public void updateProbonoProject(int probonoProjectId, String projectContent) {
		/*
		 * Board 객체는 영속성 context 영역에 저장 영속성 영역의 객체 데이터 저장 후 save - 새로운 데이터 갱신 확인 후에
		 * update
		 */
		ProbonoProject findProbonoProject = probonoProjectRepo.findById(probonoProjectId).get();

		findProbonoProject.setProjectContent(projectContent);
		probonoProjectRepo.save(findProbonoProject);
	}

	public void deleteProbonoProject(int probonoProjectId) {
		probonoProjectRepo.deleteById(probonoProjectId);
	}

}