package com.bethena.magazinefans.ui.category;

import com.bethena.magazinefans.bean.Category;
import com.bethena.magazinefans.bean.MagazineConcept;

import java.util.List;

public class CateViewModel {
    private Category category;

    private List<MagazineConcept> magas;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<MagazineConcept> getMagas() {
        return magas;
    }

    public void setMagas(List<MagazineConcept> magas) {
        this.magas = magas;
    }
}
