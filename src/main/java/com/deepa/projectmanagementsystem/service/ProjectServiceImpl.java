//package com.deepa.projectmanagementsystem.service;
//
//import com.deepa.projectmanagementsystem.model.Chat;
//import com.deepa.projectmanagementsystem.model.Project;
//
//import com.deepa.projectmanagementsystem.model.User;
//import com.deepa.projectmanagementsystem.repository.ChatRepository;
//import com.deepa.projectmanagementsystem.repository.Projectrepository;
//import com.deepa.projectmanagementsystem.service.ProjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class ProjectServiceImpl implements ProjectService{
//
//    @Autowired
//    private Projectrepository projectrepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Autowired
//    private ChatService chatservice;
//
//    @Override
//    public Project createProject(Project project, User user) throws Exception {
//       Project createdProject=new Project();
//       createdProject.setOwner(user);
//       createdProject.setTags(project.getTags());
//       createdProject.setName(project.getName());
//       createdProject.setCategory(project.getCategory());
//       createdProject.setDescription(project.getDescription());
//       createdProject.getTeam().add(user);
//
//       Project savedProject=projectrepository.save(createdProject);
//
//       Chat chat=new Chat();
//       chat.setProject(savedProject);
//       Chat projectChat=chatservice.createChat(chat);
//       savedProject.setChat(projectChat);
//       return null;
//    }
//
//    @Override
//    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
//        List<Project> projects=projectrepository.findByNameContainingAndTeamContaining(user,user);
//        if(category!=null){
//            projects= projects.stream().filter(project ->project.getCategory().equals(category))
//                    .collect(Collectors.toList());
//        }
//        return projects;
//    }
//
//    @Override
//    public Project getProjectById(Long projectId) throws Exception {
//        return null;
//    }
//
//    @Override
//    public void deleteProject(Long projectId, Long userId) throws Exception {
//
//    }
//
//    @Override
//    public Project updateProject(Project updatedProject, Long id) throws Exception {
//        return null;
//    }
//
//    @Override
//    public void addUserToProject(Long projectId, Long userId) throws Exception {
//
//    }
//
//    @Override
//    public Chat getChatByProjectId(Long projectId) throws Exception {
//        return null;
//    }
//}
package com.deepa.projectmanagementsystem.service;

import com.deepa.projectmanagementsystem.model.Chat;
import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.Projectrepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private Projectrepository projectrepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ChatService chatservice;

    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project createdProject = new Project();
        createdProject.setOwner(user);
        createdProject.setTags(project.getTags());
        createdProject.setName(project.getName());
        createdProject.setCategory(project.getCategory());
        createdProject.setDescription(project.getDescription());
        createdProject.getTeam().add(user);

        Project savedProject = projectrepository.save(createdProject);

        Chat chat = new Chat();
        chat.setProject(savedProject);
        Chat projectChat = chatservice.createChat(chat);
        savedProject.setChat(projectChat);

        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects = projectrepository.findByTeamContainingOrOwner(user, user);

        if (category != null) {
            projects = projects.stream()
                    .filter(project -> project.getCategory().equalsIgnoreCase(category))
                    .collect(Collectors.toList());
        }

        if (tag != null) {
            projects = projects.stream()
                    .filter(project -> project.getTags() != null && project.getTags().contains(tag))
                    .collect(Collectors.toList());
        }

        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
    	Optional<Project>optionalProject=projectrepository.findById(projectId);
    	if(optionalProject.isEmpty()) {
    		throw new Exception("Project not found");
    	}
    	return optionalProject.get();
    	// return projectrepository.findById(projectId)
           //     .orElseThrow(() -> new Exception("Project not found with ID: " + projectId));
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
       // Project project =
    	getProjectById(projectId);
       // if (!project.getOwner().getId().equals(userId)) {
           // throw new Exception("Only the project owner can delete the project.");
        
        projectrepository.deleteById(projectId);
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        Project existingProject = getProjectById(id);
        existingProject.setName(updatedProject.getName());
        existingProject.setDescription(updatedProject.getDescription());
        //existingProject.setCategory(updatedProject.getCategory());
        existingProject.setTags(updatedProject.getTags());
        return projectrepository.save(existingProject);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project = getProjectById(projectId);
        User user=userService.findUserbyId(userId);
        if(!project.getTeam().contains(user)) {
        	project.getChat().getUser().add(user);
        	 project.getTeam().add(user);
        	
        }
        
        projectrepository.save(project);
    }
    @Override
    public void removeUserFromProject(Long projectId,Long userId) throws Exception{
    	 Project project = getProjectById(projectId);
         User user=userService.findUserbyId(userId);
         if(!project.getTeam().contains(user)) {
         	project.getChat().getUser().remove(user);
         	 project.getTeam().remove(user);
         	
         }
         
         projectrepository.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project = getProjectById(projectId);
        
        return project.getChat();
    }

	@Override
	public List<Project> searchProjects(String keyword, User user) throws Exception {
		String partialName="%"+keyword+"%";
		List<Project> projects=projectrepository.findByNameContainingAndTeamContaining(keyword, user);
		
		
		return projects;
	}
}
