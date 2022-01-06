package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.Course;
import com.MultipleTableFetch.Entity.PushNotificationRequest;
import com.MultipleTableFetch.Entity.Users;
import com.MultipleTableFetch.Service.CourseService;
import com.MultipleTableFetch.Service.PushNotificationService;
import com.MultipleTableFetch.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PushNotificationController {
    @Autowired
    UserService userService;
    @Autowired
    CourseService courseService;

    private PushNotificationService pushNotificationService;

    public PushNotificationController(PushNotificationService pushNotificationService) {
        this.pushNotificationService = pushNotificationService;
    }

    @PostMapping("/notification/token")
    public ResponseEntity sendTokenNotification(@RequestBody PushNotificationRequest request) {
        Users userUsingId = userService.getUserUsingId(request.getUserId());
        if (userUsingId == null)
            return ResponseHandler.response("", "USER NOT FOUND", false, HttpStatus.BAD_REQUEST);
        Course course = courseService.getCourse(request.getCourseId());
        if (course == null)
            return ResponseHandler.response("", "COURSE NOT FOUND.", false, HttpStatus.BAD_REQUEST);

        pushNotificationService.sendPushNotificationToToken(request);
        System.out.println("prince");
       // return new ResponseEntity<>(new PushNotificationResponse(HttpStatus.OK.value(), ""), HttpStatus.OK);
        return ResponseHandler.response("", "Notification has been sent.", true, HttpStatus.OK);

    }
}