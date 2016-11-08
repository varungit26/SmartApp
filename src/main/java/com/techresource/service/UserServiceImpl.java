package com.techresource.service;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techresource.dao.UserDAO;
import com.techresource.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	/*
	 * static { users = populateDummyUsers(); }
	 */

	public List<User> findAllUsers() {
		logger.info("Fetching all user Information");
		return userDAO.findAllUsers();
	}

	public User findById(long id) {

		logger.info("Retrieving User based on id :" + id);
		return userDAO.findById(id);
	}

	public User findByName(String username) {
		logger.info("Retrieving User based on username :" + username);
		return userDAO.findByName(username);
	}

	public void saveUser(User user) {
		logger.info("Saving user.");
		userDAO.saveUser(user);
	}

	public void updateUser(User user) {

		User currentUser = findById(user.getId());
		currentUser.setUserName(user.getUserName());
		currentUser.setFirstName(user.getFirstName());
		currentUser.setLastName(user.getLastName());
		currentUser.setPassword(user.getPassword());

		userDAO.saveUser(currentUser);
		/*int index = users.indexOf(user);
		users.set(index, user);*/
	}

	public void deleteUserById(long id) {

		for (Iterator<User> iterator = users.iterator(); iterator.hasNext();) {
			User user = iterator.next();
			if (user.getId() == id) {
				iterator.remove();
			}
		}
	}

	public boolean isUserExist(User user) {
		return (findByName(user.getUserName()) != null);
	}

	/*
	 * private static List<User> populateDummyUsers() { List<User> users = new
	 * ArrayList<User>(); users.add(new User(counter.incrementAndGet(), "Sam",
	 * 30, 70000)); users.add(new User(counter.incrementAndGet(), "Tom", 40,
	 * 50000)); users.add(new User(counter.incrementAndGet(), "Jerome", 45,
	 * 30000)); users.add(new User(counter.incrementAndGet(), "Silvia", 50,
	 * 40000)); return users; }
	 */
	public void deleteAllUsers() {
		users.clear();
	}

}
