package com.motoshop.persistance.factories;

import com.motoshop.model.Motorcycle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class MotorcycleFactory {
    private int ID_POS = 1;
    private int TITLE_POS = 2;
    private  int TYPE_POS = 3;
    private int MILEAGE_POS = 4;
    private int DATE_POS = 5;
    private int PRICE_POS = 6;
    private int ENGINE_SIZE_POS = 7;

    private String ID = "ID";
    private String TITLE = "TITLE";
    private String TYPE = "TYPE";
    private String MILEAGE = "MILEAGE";
    private String YEAR = "YEAR";
    private String PRICE = "PRICE";
    private String ENGINE_SIZE = "ENGINE_SIZE";


    public Motorcycle build(ResultSet rs, Map<Integer, String> types) throws SQLException {
        Motorcycle result = new Motorcycle();
        result.setId(rs.getInt(ID));
        result.setTitle(rs.getString(TITLE));
        result.setType(types.get(rs.getInt(TYPE)));
        result.setType_id(rs.getInt(TYPE));
        result.setMileage(rs.getInt(MILEAGE));
        result.setYear(rs.getDate(YEAR));
        result.setPrice(rs.getDouble(PRICE));
        result.setEngineSize(rs.getInt(ENGINE_SIZE));
        return result;
    }

    public Motorcycle build(Object[] object) {
        Motorcycle motorcycle = new Motorcycle();
        motorcycle.setId((int)object[ID_POS]);
        motorcycle.setTitle(object[TITLE_POS].toString());
        motorcycle.setType(object[TYPE_POS].toString());
        motorcycle.setMileage((int)object[MILEAGE_POS]);
        motorcycle.setPrice((Double) object[PRICE_POS]);
        motorcycle.setEngineSize((int)object[ENGINE_SIZE_POS]);
        return motorcycle;
    }
}
