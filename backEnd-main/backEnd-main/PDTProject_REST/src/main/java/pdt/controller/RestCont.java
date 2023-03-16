package pdt.controller;

import java.util.List;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.firebase.auth.FirebaseAuthException;

import pdt.dao.IlikeRepository;
import pdt.dao.PostRepository;
import pdt.dao.ReplyRepository;
import pdt.dao.UserRepository;
import pdt.entity.Post;
import pdt.entity.Reply;
import pdt.entity.User;
import pdt.firebase.FirebaseService;
import pdt.service.IlikeServiceImp;
import pdt.service.PostServiceImp;
import pdt.service.ReplyService;
import pdt.service.UserServiceImp;

@SessionAttributes("user")
@RestController
public class RestCont {

	@Autowired
	PostServiceImp postService;

	@Autowired
	PostRepository postRepository;

	@Autowired
	UserServiceImp userService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	IlikeRepository ilikeRepository;

	@Autowired
	IlikeServiceImp ilikeService;

	@Autowired
	ReplyService replyService;

	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	FirebaseService firebaseService;

	@RequestMapping("/createuser")
	public String createUser(@RequestBody User user) throws InterruptedException, ExecutionException {

		return firebaseService.saveUserDetails(user);
	}

	@RequestMapping("/getuserinform")
	public User getUser(@RequestParam String userId)
			throws InterruptedException, ExecutionException, FirebaseAuthException {

		User a = firebaseService.getUserDetails(userId);
		userService.insertUser(a);

		return a;
	}

//	@GetMapping("/logout")
//	public String logout(SessionStatus status) {
//		status.setComplete();
//		return "redirect:index.html";
//	}

	@RequestMapping("/gohome")
	public List<Post> goHome() {// @ModelAttribute("user") User user, Model model) {

		List<Post> postList = postRepository.getPostList();
		// List<Post> postList = postService.getPostList();

		return postList;
	}

	@RequestMapping("/write")
	public List<Post> write(@ModelAttribute("user") User user, Model model, @ModelAttribute("post") Post post) {

		if (post.getKeyword().length() != 0) {

			String[] arr = post.getKeyword().split(",");
			int a = arr.length;
			if (a == 1) {
				post.setKeyword1(arr[0]);
			} else if (a == 2) {
				post.setKeyword1(arr[0]);
				post.setKeyword2(arr[1]);
			} else {

				post.setKeyword1(arr[0]);
				post.setKeyword2(arr[1]);
				post.setKeyword3(arr[2]);
			}
		}
		postService.insertPost(post);
		List<Post> postList = postRepository.getPostList();
		return postList;
		// model.addAttribute("postList", postList);

	}

//	@RequestMapping("/gowrite")
//	public String gowrite(@ModelAttribute("user") User user, Model model) {
//		// System.out.println(model.getAttribute("user"));
//		return "write";
//	}

	@RequestMapping("/ilike")
	public Long addLike(@RequestParam("userId") User user, Model model, @RequestParam("postId") Post post) {
		boolean a = ilikeService.addLike(user.getUserId(), post);
		System.out.println(a);
		if (a) {
			postRepository.countLike(post.getPostId());

			return postRepository.returnLike(post.getPostId());
		} else {
			ilikeService.deleteByUserIdAndPostId(user, post);
			postRepository.countLike(post.getPostId());
		}
		return postRepository.returnLike(post.getPostId());

	}

	@RequestMapping("/goreply")
	public List<Reply> goReply(@RequestParam("postId") Post post, Model model) {

		Post findPost = postService.getPost(post);
		List<Reply> replyList = replyRepository.findByPostId(findPost);

		return replyList;
	}

	@RequestMapping("/reply")
	public List<Post> reply(Model model, Reply reply) {

		replyService.insertReply(reply);
		List<Post> postList = postRepository.getPostList();

		return postList;
	}

//	@RequestMapping("/goreplywrite")
//	public String goReplyWrite(@ModelAttribute("postId") Post post, Model model) {
//
//		return "replywrite";
//	}

	@RequestMapping("/getuser")
	public List<Post> getUser(@RequestParam("user") User user, Model model) {

		User findUser = userService.getUser(user);
		List<Post> postList = postRepository.getPostListWithUserId(findUser);

		return postList;
	}

	@RequestMapping("/goprofile")
	public List<Post> goProfile(@RequestParam("user") User user, Model model) {

		User findUser = userService.getUser(user);
		List<Post> postList = postRepository.getPostListWithUserId(findUser);

		return postList;
	}

	@RequestMapping("/updateprofile")
	public User updateProfile(User user, Model model) {

		userService.updateUser(user);
		User findUser = userService.getUser(user);

		return findUser;
	}

	@RequestMapping("/goupdatepost")
	public Post goUpdatePost(@RequestParam("postId") Post post, Model model) {

		Post findPost = postService.getPost(post);
		model.addAttribute("post", findPost);

		return findPost;
	}

	@RequestMapping("/updatepost")
	public List<Post> updatePost(Post post, Model model) {

		postService.updatePost(post);
		User findUser = userService.getUser(post.getUserId());
		List<Post> postList = postRepository.getPostListWithUserId(findUser);

		return postList;
	}

	@RequestMapping("/deletepost")
	public List<Post> deletePost(Post post, Model model, User user) {

		postService.deletePost(post);
		List<Post> postList = postRepository.getPostListWithUserId(user);

		return postList;
	}

//	@RequestMapping("/getalluser")
//	public void getAllUser() throws InterruptedException, ExecutionException {
//		firebaseService.getAllUser();
//
//	}

	@RequestMapping("/countkeyword")
	public List<Map<String, Object>> countKeyword() {

		List<Map<String, Object>> keywordList = postRepository.countKeyword();

		return keywordList;
	}

	@RequestMapping("/searchkeyword")
	public List<Post> searcKeyword(@RequestParam("keyword") String keyword) {

		System.out.println(keyword);
		List<Post> searchList = postRepository.searchKeyword(keyword);

		return searchList;
	}

}
