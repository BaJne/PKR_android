package com.example.medenjak.data;

import com.example.medenjak.R;
import com.example.medenjak.model.Product;

import java.util.ArrayList;

class ProductService {

    private static ProductService instance;
    public static ProductService getInstance(){
        if(instance == null){
            instance = new ProductService();
        }
        return instance;
    }

    private final ArrayList<Product> products;
    private Product product = null;

    private ProductService(){
        products = new ArrayList<>();
        init();
    }

    private void init() {
        products.add(new Product("Livadski med", "Livadski med jača organizam i povećava zaštitne i imune mehanizme. Pokazuje dobro dejstvo kod oboljenja disajnih puteva, gnojnih rana, i ginekoloških oboljenja. Kao sredstvo za iskašljavanje uzima se u vidu inhalacija, sirupa, ili u prirodnom obliku. Vrlo dobro deluje za lečenje hroničnih formi rinitisa, sinusitisa kao i stomačno-crevnih i bubrežnih bolesti.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 1200, R.drawable.honey0));

        products.add(new Product("Livadski med", "Livadski med jača organizam i povećava zaštitne i imune mehanizme. Pokazuje dobro dejstvo kod oboljenja disajnih puteva, gnojnih rana, i ginekoloških oboljenja. Kao sredstvo za iskašljavanje uzima se u vidu inhalacija, sirupa, ili u prirodnom obliku. Vrlo dobro deluje za lečenje hroničnih formi rinitisa, sinusitisa kao i stomačno-crevnih i bubrežnih bolesti.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 820, R.drawable.honey1));

        products.add(new Product("Livadski med", "Livadski med jača organizam i povećava zaštitne i imune mehanizme. Pokazuje dobro dejstvo kod oboljenja disajnih puteva, gnojnih rana, i ginekoloških oboljenja. Kao sredstvo za iskašljavanje uzima se u vidu inhalacija, sirupa, ili u prirodnom obliku. Vrlo dobro deluje za lečenje hroničnih formi rinitisa, sinusitisa kao i stomačno-crevnih i bubrežnih bolesti.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 900, R.drawable.honey2));

        products.add(new Product("Bagremov med", "Bagremov med je odlično kardiotonično i neurotonično sredstvo. Ova vrsta meda preporučuje se kod stomačno-crevnih oboljenja, smetnji u varenju, oboljenja jetre i bubrega, a deluje i umirujuće na organizam.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 750, R.drawable.honey5));

        products.add(new Product("Šumski med", "Šumski med se posebno preporučuje kao dodatak ishrani tokom lečenja od anemije. Ima antiseptičko, laksativno i umirujuće dejstvo. Zbog svog specifičnog sastava ovaj med efikasno poboljšava rad jetre i bubrega.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 1000, R.drawable.honey3));

        products.add(new Product("Saće u medu", "Saće u medu je izuzetno korisno za sluzokožu disajnih organa. Utičepovoljno na regeneraciju plućnog tkiva, te se preporučuje pušačima. Vosak iz saća deluje anti-alergijski. ", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 820, R.drawable.honey6));

        products.add(new Product("Medovača", "Medovača je domaća prepečenica sa odabranim medom – slatko piće koje, kako se pokazalo, najviše vole dame.", "", 700, R.drawable.honey7));

        products.add(new Product("Suncokretov med", "Suncokretov med se preporučuje:\n" +
                "\n" +
                "    kod poremećaja u radu srca i krvnih sudova,\n" +
                "    ljudima koji pate od visokog krvnog pritiska,\n" +
                "    u slučajevima aritmije i loše cirkulacije.\n" +
                "    Zbog prirodnog bogatstva polenom preporučuje se kod prevencije problema sa prostatom.\n", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 820, R.drawable.honey4));

        products.add(new Product("Bronhi med", "Bronhi med je mešavina lekovitog bilja i bagremovog meda. Korisna je kod čestih respiratornih infekcija dece i odraslih. Ublažava i otklanja probleme sa disajnim organima.", "Med treba raspodeliti u 3 dela pola sata pre obroka i treba ga što duže otapati u ustima", 820, R.drawable.honey8));

    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void checkProductDetails(int position){
        product = products.get(position);
    }

    public Product getProduct() {
        return product;
    }
}
