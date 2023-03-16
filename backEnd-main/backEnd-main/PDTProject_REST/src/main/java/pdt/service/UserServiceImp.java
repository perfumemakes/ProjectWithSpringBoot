package pdt.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pdt.dao.UserRepository;
import pdt.entity.User;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepository userRepo;

	public List<User> getUserList(User user) {
		return (List<User>) userRepo.findAll();
	}

	public void insertUser(User user) {
		userRepo.save(user);
	}

	public User getUser(User user) {
		//System.out.println(userRepo.findById(user.getUserId()).get());
		return userRepo.findById(user.getUserId()).get();
	}

	public void updateUser(User user) {
		/*
		 * Board 객체는 영속성 context 영역에 저장 영속성 영역의 객체 데이터 저장 후 save - 새로운 데이터 갱신 확인 후에
		 * update
		 * 
		 */
		User findUser = userRepo.findById(user.getUserId()).get();

		//findUser.setPw(user.getPw());
		findUser.setName(user.getName());
		//findUser.setImgUrl(user.getImgUrl());
		userRepo.save(findUser);
	}

	public void deleteUser(User user) {
		userRepo.deleteById(user.getUserId());
	}

}
