package com.zosh.projectmanagmentsystem.repository;

import com.zosh.projectmanagmentsystem.modal.Project;
import com.zosh.projectmanagmentsystem.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectRepo extends JpaRepository<Project, Long> {
    List<Project> findByOwner(User user);
    List<Project> findByNameAndTeamContains(String partialName, User team);
    @Query("SELECT p FROM Project p JOIN p.team t WHERE t=:user")
    List<Project> findProjectByTeam(@Param("user") User user);
    List<Project> findByTeamContainingOrOwner(User user, User owner);
}
