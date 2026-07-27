package com.Lab04Backend.TaskFlow.activities.controllers;

import com.Lab04Backend.TaskFlow.activities.dtos.ActivityResponseDTO;
import com.Lab04Backend.TaskFlow.activities.exceptions.UserNotFoundException;
import com.Lab04Backend.TaskFlow.activities.services.ActivityService;
import com.Lab04Backend.TaskFlow.user.entity.User;
import com.Lab04Backend.TaskFlow.user.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Activities")
@RestController
@RequestMapping("/api/activities")
@RequiredArgsConstructor
public class ActivityController {

    private final ActivityService activityService;
    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    public List<ActivityResponseDTO> getLastActivities(
            @PathVariable String userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new UserNotFoundException(
                                "User whith id '" + userId + "' not  found."
                        )
                );

        return activityService.findLastActivities(user);
    }

}