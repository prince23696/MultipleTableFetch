package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Banner;
import com.MultipleTableFetch.Entity.StatusEnum;
import com.MultipleTableFetch.Repository.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerServiceImpl implements BannerService {

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
}