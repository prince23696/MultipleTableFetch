package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Banner;
import com.MultipleTableFetch.Entity.StatusEnum;
import com.MultipleTableFetch.Repository.BannerRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

    private static Logger logger = LogManager.getLogger(BannerServiceImpl.class);

    @Autowired
    BannerRepository bannerRepository;

    @Override
    public List<Banner> getAllBanner() {
        return bannerRepository.findAll();
    }

    @Override
    public Banner getBanner(Long id) {
        return bannerRepository.findById(id).get();
    }

    @Override
    public Banner addBanner(String bannerName, String webImageUrl, String mobileImageUrl) {
        Banner banner = new Banner();
        banner.setBannerName(bannerName);
        banner.setWebImageUrl(webImageUrl);
        banner.setMobileImageUrl(mobileImageUrl);
        banner.setStatus("INACTIVE");
        bannerRepository.save(banner);
        return banner;
    }

    @Override
    public Banner updateBanner(Long id, String bannerName, String webImageUrl, String mobileImageUrl, StatusEnum statusEnum) {
        Banner banner = bannerRepository.findById(id).get();
        banner.setBannerName(bannerName);
        banner.setWebImageUrl(webImageUrl);
        banner.setMobileImageUrl(mobileImageUrl);
        String s = String.valueOf(statusEnum);
        banner.setStatus(s);
        bannerRepository.save(banner);
        return banner;
    }

    @Override
    public Banner deleteBanner(Long id) {
        Banner banner = bannerRepository.findById(id).get();
        bannerRepository.deleteById(id);
        return banner;
    }

    @Override
   // @Scheduled(initialDelay = 1000, fixedRate = 3000)
    public void run() {
       // logger.info("Current time is :: " + Calendar.getInstance().getTime());
    }
}