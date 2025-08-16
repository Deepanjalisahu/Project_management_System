/*package com.deepa.projectmanagementsystem.repository;

import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Projectrepository extends JpaRepository<Project,Long> {
  List<Project> findByOwner(User user);

  List<Project> findByNameContainingAndTeamContaining(User user2,User user);

  @Query("select p From Project p join p.team t where t=:user")
  List<Project> findProjectByTeam(@Param("user")User user);

  List<Project> findbyTeamContainingOrOwner(User user,User owner);

}*/

package com.deepa.projectmanagementsystem.repository;

import com.deepa.projectmanagementsystem.model.Project;
import com.deepa.projectmanagementsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Projectrepository extends JpaRepository<Project, Long> {

    // Get all projects by owner
    List<Project> findByOwner(User user);

    // Get projects by team members containing and name containing (optional)
    List<Project> findByNameContainingAndTeamContaining(String name, User user);

    // Custom query to find projects by team
  //  @Query("SELECT p FROM Project p JOIN p.team t WHERE t = :user")
    //List<Project> findProjectByTeam(@Param("user") User user);

    // ✅ Corrected method (was causing error before)
    List<Project> findByTeamContainingOrOwner(User user, User owner);
}

