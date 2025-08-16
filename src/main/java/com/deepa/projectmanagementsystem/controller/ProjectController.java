// package com.deepa.projectmanagementsystem.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.DeleteMapping;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PatchMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestHeader;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import com.deepa.projectmanagementsystem.model.Chat;
// import com.deepa.projectmanagementsystem.model.Invitation;
// import com.deepa.projectmanagementsystem.model.Project;
// import com.deepa.projectmanagementsystem.model.User;
// import com.deepa.projectmanagementsystem.repository.Inviterequest;
// import com.deepa.projectmanagementsystem.response.MessageResponse;
// import com.deepa.projectmanagementsystem.service.InvitationService;
// import com.deepa.projectmanagementsystem.service.ProjectService;
// import com.deepa.projectmanagementsystem.service.UserService;

// @RestController
// @RequestMapping("/api/projects")
// public class ProjectController {
	
// 	@Autowired
// 	private ProjectService projectservice;
	
// 	@Autowired
// 	private UserService userService;
	
// 	@Autowired
// 	private InvitationService invitationService ;
	
// 	@GetMapping
// 	public ResponseEntity<List<Project>>getProjects(
// 			@RequestParam(required=false)String category,
// 			@RequestParam(required=false)String tag,
// 			@RequestParam(required=false)String jwt
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		List<Project> projects=projectservice.getProjectByTeam(user, category, tag);
// 		return new ResponseEntity<>(projects,HttpStatus.OK);
// 	}

// 	@GetMapping("/{projectId}")
// 	public ResponseEntity<Project>getProjectsById(
// 			@PathVariable Long projectId,
// 			@RequestHeader("Authorization") String jwt
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		Project  project=projectservice.getProjectById(projectId);
// 		return new ResponseEntity<>(project,HttpStatus.OK);
// 	}
	
// 	@PostMapping
// 	public ResponseEntity<Project>CreateProjects(
// 			@PathVariable Long projectId,
// 			@RequestHeader("Authorization") String jwt,
// 			@RequestBody Project project
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		Project  CreateProject=projectservice.createProject(project, user);
// 		return new ResponseEntity<>(CreateProject,HttpStatus.OK);
// 	}
	
// 	@PatchMapping("/{projectId}")
// 	public ResponseEntity<Project>UpdateProjects(
// 			@PathVariable Long projectId,
// 			@RequestHeader("Authorization") String jwt,
// 			@RequestBody Project project
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		Project  UpdatedProject=projectservice.updateProject(project, projectId);
// 		return new ResponseEntity<>(UpdatedProject,HttpStatus.OK);
// 	}


// 	@DeleteMapping("/{projectId}")
// 	public ResponseEntity<MessageResponse>DeletedProjects(
// 			@PathVariable Long projectId,
// 			@RequestHeader("Authorization") String jwt
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		projectservice.deleteProject(projectId, user.getId());
// 		MessageResponse res = new MessageResponse("project deleted successfully");
// 		return new ResponseEntity<>(res,HttpStatus.OK);
// 	}
	

// 	@GetMapping("/search")
// 	public ResponseEntity<List<Project>>SearchProjects(
// 			@RequestParam(required=false)String keyword,
			
// 			@RequestHeader("Authorization")String jwt
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		List<Project> projects=projectservice.searchProjects(keyword,user);
// 		return new ResponseEntity<>(projects,HttpStatus.OK);
// 	}
// 	@GetMapping("/{projectId}")
// 	public ResponseEntity<Chat>getChatByProjectId(
// 			@PathVariable Long projectId,
// 			@RequestHeader("Authorization") String jwt
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		Chat  chat=projectservice.getChatByProjectId(projectId);
// 		return new ResponseEntity<>(chat,HttpStatus.OK);
// 	}
	
// 	@PostMapping("/invite")
// 	public ResponseEntity<MessageResponse>inviteProject(
// 			@RequestBody Inviterequest req,
// 			@RequestHeader("Authorization") String jwt,
// 			@RequestBody Project project
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		Project createproject=projectservice.createProject(project,user);
// 		invitationService.sendInvitation(req.getEmail(), req.getProjectId());
// 	    MessageResponse res=new MessageResponse("User invitation sent");
// 	    	return new ResponseEntity<>(res,HttpStatus.OK);
// 	}
// 	@GetMapping("/accep_tinvite")
// 	public ResponseEntity<Invitation>acceptinviteProject(
// 			@RequestParam String token,
// 			@RequestHeader("Authorization") String jwt,
// 			@RequestBody Project project
// 		) throws Exception
// 	{
// 		User user=userService.findUserProfileByjwt(jwt);
// 		//Project createproject=projectservice.createProject(project,user);
//     	Invitation invitation=	invitationService.acceptInvitation(token, user.getId());
// 		projectservice.addUserToProject(invitation.getProjectid(),user.getId());
		
// 	   // MessageResponse res=new MessageResponse("User invitation sent");
// 	    	return new ResponseEntity<>(invitation,HttpStatus.OK);
// 	}
// }

package com.deepa.projectmanagementsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.deepa.projectmanagementsystem.model.Chat;
import com.deepa.projectmanagementsystem.model.Invitation;
import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;
import com.deepa.projectmanagementsystem.repository.Inviterequest;
import com.deepa.projectmanagementsystem.response.MessageResponse;
import com.deepa.projectmanagementsystem.service.InvitationService;
import com.deepa.projectmanagementsystem.service.ProjectService;
import com.deepa.projectmanagementsystem.service.UserService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectservice;

    @Autowired
    private UserService userService;

    @Autowired
    private InvitationService invitationService;

    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        List<Project> projects = projectservice.getProjectByTeam(user, category, tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectsById(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        Project project = projectservice.getProjectById(projectId);
        return new ResponseEntity<>(project, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Project> createProjects(
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        Project createdProject = projectservice.createProject(project, user);
        return new ResponseEntity<>(createdProject, HttpStatus.OK);
    }

    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProjects(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt,
            @RequestBody Project project
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        Project updatedProject = projectservice.updateProject(project, projectId);
        return new ResponseEntity<>(updatedProject, HttpStatus.OK);
    }

    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProjects(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        projectservice.deleteProject(projectId, user.getId());
        MessageResponse res = new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProjects(
            @RequestParam(required = false) String keyword,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        List<Project> projects = projectservice.searchProjects(keyword, user);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }

    // ✅ Fixed Path: Avoided conflict with /{projectId}
    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable Long projectId,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        Chat chat = projectservice.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat, HttpStatus.OK);
    }

    // ✅ Fixed: Only one @RequestBody and removed unused project field
    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestBody Inviterequest req,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        invitationService.sendInvitation(req.getEmail(), req.getProjectId());
        MessageResponse res = new MessageResponse("User invitation sent");
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    // ✅ Fixed: Removed @RequestBody from GET, renamed endpoint
    @GetMapping("/accept-invite")
    public ResponseEntity<Invitation> acceptInviteProject(
            @RequestParam String token,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user = userService.findUserProfileByjwt(jwt);
        Invitation invitation = invitationService.acceptInvitation(token, user.getId());
        projectservice.addUserToProject(invitation.getProjectid(), user.getId());
        return new ResponseEntity<>(invitation, HttpStatus.OK);
    }
}
