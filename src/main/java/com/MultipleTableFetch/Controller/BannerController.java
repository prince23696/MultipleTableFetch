package com.MultipleTableFetch.Controller;

import com.MultipleTableFetch.Dto.ResponseHandler;
import com.MultipleTableFetch.Entity.Banner;
import com.MultipleTableFetch.Entity.StatusEnum;
import com.MultipleTableFetch.Helper.FileUploadHelper;
import com.MultipleTableFetch.Service.AllService;
import com.MultipleTableFetch.Service.BannerService;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@RestController
public class BannerController {

    @Autowired
    BannerService bannerService;
    @Autowired
    FileUploadHelper fileUploadHelper;
    @Autowired
    AllService allService;

    @GetMapping("getBannerByBannerId")
    public ResponseEntity<Object> getCreateBanner(@RequestParam Long id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Banner banner = bannerService.getBanner(id);
        return ResponseHandler.response(banner, "Successfully Getting Banner Details", true, HttpStatus.OK);
    }

    @GetMapping("/getAllBanner")
    public ResponseEntity<Object> getAllCreateBanner(@RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        List<Banner> allBanner = bannerService.getAllBanner();
        return ResponseHandler.response(allBanner, "Successfully Getting All Banner Details", true, HttpStatus.OK);

    }

    @PostMapping(path = "/createBanner", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Object> uploadQuiz(@RequestParam String bannerName, @RequestPart("webImageUrl") @ApiParam(value = "webImageUrl", required = true) MultipartFile webImageUrl, @RequestPart("mobileImageUrl") @ApiParam(value = "mobileImageUrl", required = true) MultipartFile mobileImageUrl, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws IOException {

        if (webImageUrl.getSize() > 25000 || mobileImageUrl.getSize() > 25000) {
            return ResponseHandler.response("", "Could not upload the file: %s! As Size Exceed 25Kb", false, HttpStatus.BAD_REQUEST);
        } else {
            if (webImageUrl.isEmpty() || mobileImageUrl.isEmpty()) {
                return ResponseHandler.response("", "Request Must Contain File.", false, HttpStatus.INTERNAL_SERVER_ERROR);
            } else if (allService.checkImage(webImageUrl) && allService.checkImage(mobileImageUrl)) {
                String[] s = fileUploadHelper.uploadFile1(webImageUrl, mobileImageUrl);
                if (s != null) {
                    Banner banner = bannerService.addBanner(bannerName, s[0], s[1]);
                    return ResponseHandler.response(banner, "Successfully Added Banner Details", true, HttpStatus.OK);
                }
            } else
                return ResponseHandler.response("", "Could not upload the file", false, HttpStatus.BAD_REQUEST);
            return null;
        }
    }

    @DeleteMapping("/deleteBanner")
    public ResponseEntity<Object> deleteBanner(@RequestParam Long
                                                       id, @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        Banner banner = bannerService.deleteBanner(id);
        return ResponseHandler.response(banner, "Successfully Performed Delete Operation.", true, HttpStatus.OK);
    }

    @PutMapping("/updateBanner")
    public ResponseEntity<Object> updateBanner(@RequestParam Long id, @RequestParam String bannerName, @RequestPart("webImageUrl") @ApiParam(value = "webImageUrl", required = true) MultipartFile webImageUrl, @RequestPart("mobileImageUrl") @ApiParam(value = "mobileImageUrl", required = true) MultipartFile mobileImageUrl, @RequestParam StatusEnum statusEnum, @RequestHeader(name = "Accept-Language", required = false) Locale locale) throws IOException {
        String[] s = fileUploadHelper.uploadFile1(webImageUrl, mobileImageUrl);
        if (s != null) {
            Banner banner1 = bannerService.updateBanner(id, bannerName, s[0], s[1], statusEnum);
            return ResponseHandler.response(banner1, "Successfully Updated Banner Details", true, HttpStatus.OK);
        } else
            return ResponseHandler.response("", "Could not upload the file: %s!" + webImageUrl.getOriginalFilename(), false, HttpStatus.BAD_REQUEST);
    }
}