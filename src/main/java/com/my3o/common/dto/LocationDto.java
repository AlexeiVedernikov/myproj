package com.my3o.common.dto;

public class LocationDto {

    private String id;

    private CountryDto countryDto;
    private RegionDto regionDto;
    private DistrictDto districtDto;
    private TownDto townDto;
    private TownAreaDto townAreaDto;

    public String getId() {
        return id;
    }

    public CountryDto getCountryDto() {
        return countryDto;
    }

    public RegionDto getRegionDto() {
        return regionDto;
    }

    public DistrictDto getDistrictDto() {
        return districtDto;
    }

    public TownDto getTownDto() {
        return townDto;
    }

    public TownAreaDto getTownAreaDto() {
        return townAreaDto;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCountryDto(CountryDto countryDto) {
        this.countryDto = countryDto;
    }

    public void setRegionDto(RegionDto regionDto) {
        this.regionDto = regionDto;
    }

    public void setDistrictDto(DistrictDto districtDto) {
        this.districtDto = districtDto;
    }

    public void setTownDto(TownDto townDto) {
        this.townDto = townDto;
    }

    public void setTownAreaDto(TownAreaDto townAreaDto) {
        this.townAreaDto = townAreaDto;
    }

    @Override
    public String toString() {
        return "LocationDto [id=" + id + ", countryDto=" + countryDto + ", regionDto=" + regionDto + ", districtDto="
                + districtDto + ", townDto=" + townDto + ", townAreaDto=" + townAreaDto + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LocationDto that = (LocationDto) o;
        if (!countryDto.equals(that.countryDto)) return false;
        if (!regionDto.equals(that.regionDto)) return false;
        if (!(districtDto == null && that.districtDto == null)) {
            if (!districtDto.equals(that.districtDto)) return false;
        }
        if (!(townDto == null && that.townDto == null)) {
            if (!townDto.equals(that.townDto)) return false;
        }
        if (!(townAreaDto == null && that.townAreaDto == null)) {
            if (!townAreaDto.equals(that.townAreaDto)) return false;
        }

        return true;
    }
}
