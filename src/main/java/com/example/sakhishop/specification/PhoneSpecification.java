package com.example.sakhishop.specification;

import com.example.sakhishop.entity.BrandEntity_;
import com.example.sakhishop.entity.ColorEntity_;
import com.example.sakhishop.entity.phone.MemoryEntity_;
import com.example.sakhishop.entity.phone.Phone;
import com.example.sakhishop.entity.phone.PhoneDetails_;
import com.example.sakhishop.entity.phone.Phone_;
import org.springframework.data.jpa.domain.Specification;

public class PhoneSpecification {

    public static Specification<Phone> getSpec(Integer min, Integer max, String brand, String color, Integer minYear, Integer maxYear,Integer memory) {

        Specification<Phone> spec = null;
        Specification<Phone> temp = null;

        if (min != null && max != null) {
            spec = getPrice(min, max);
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (brand != null) {
            spec = getBrand(brand);
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (color != null) {
            spec = getColor(color);
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (minYear != null && maxYear != null) {
            spec = getYear(minYear, maxYear);
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (memory != null) {
            spec = getMemory(memory);
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (min != null && max != null && brand != null) {
            spec = Specification.where(getPrice(min, max).and(getBrand(brand)));
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (min != null && max != null && color != null) {
            spec = Specification.where(getPrice(min, max).and(getColor(color)));
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (min != null && max != null && brand != null && color != null) {
            spec = Specification.where(getPrice(min, max).and(getBrand(brand)).and(getColor(color)));
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (min != null && max != null && brand != null && color != null && minYear != null && maxYear != null) {
            spec = Specification.where(getPrice(min, max).and(getBrand(brand)).and(getColor(color)).and(getYear(minYear, maxYear)));
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        if (min != null && max != null && brand != null && color != null && minYear != null && maxYear != null && memory!=null) {
            spec = Specification.where(getPrice(min, max).and(getBrand(brand)).and(getColor(color)).and(getYear(minYear, maxYear)).and(getMemory(memory)));
            temp = spec != null ? Specification.where(spec).and(temp) : temp;
        }
        return spec;
    }

    public static Specification<Phone> getPrice(Integer min, Integer max) {
        return (root, query, cb) -> cb.between((root.get(Phone_.price).as(Integer.class)), min, max);
    }

    public static Specification<Phone> getBrand(String brand) {
        return (root, query, cb) -> cb.like(cb.lower(root.join(Phone_.phone).join(PhoneDetails_.brand).get(BrandEntity_.NAME)), "%" + brand.toLowerCase() + "%");
    }

    public static Specification<Phone> getColor(String color) {
        return (root, query, cb) -> cb.like(cb.lower(root.join(Phone_.color).get(ColorEntity_.NAME)), "%" + color.toLowerCase() + "%");
    }

    public static Specification<Phone> getYear(Integer minYear, Integer maxYear) {
        return (root, query, cb) -> cb.between((root.join(Phone_.phone).get(PhoneDetails_.year).as(Integer.class)), minYear, maxYear);
    }

    public static Specification<Phone> getMemory(Integer memory) {
        return (root, query, cb) -> cb.equal((root.join(Phone_.memory).get(MemoryEntity_.memory)), memory);
    }
}
