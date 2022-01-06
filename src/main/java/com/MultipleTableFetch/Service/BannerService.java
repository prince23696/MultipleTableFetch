package com.MultipleTableFetch.Service;

import com.MultipleTableFetch.Entity.Banner;
import com.MultipleTableFetch.Entity.StatusEnum;

import java.util.List;

public interface BannerService {


    public List<Banner> getAllBanner();

    public Banner getBanner(Long id);

    public Banner addBanner(String bannerName, String webImageUrl, String mobileImageUrl);

    public Banner updateBanner(Long id, String bannerName, String webImageUrl, String mobileImageUrl, StatusEnum statusEnum);

    public Banner deleteBanner(Long id);

    public void run();
}
