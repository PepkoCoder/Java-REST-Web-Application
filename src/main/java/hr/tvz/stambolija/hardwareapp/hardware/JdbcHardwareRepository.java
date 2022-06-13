package hr.tvz.stambolija.hardwareapp.hardware;

import org.springframework.context.annotation.Primary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Primary
@Repository
public class JdbcHardwareRepository implements HardwareRepository{

    private static final String SELECT_ALL = "" +
            "SELECT code, name, price, type, amountInStorage FROM hardware";

    private final JdbcTemplate jdbc;
    private final SimpleJdbcInsert inserter;

    public JdbcHardwareRepository (JdbcTemplate jdbc){
        this.jdbc = jdbc;
        this.inserter = new SimpleJdbcInsert(jdbc)
                .withTableName("hardware")
                .usingGeneratedKeyColumns("id");
    }

    @Override
    public Set<Hardware> finaAll() {
        return Set.copyOf(jdbc.query(SELECT_ALL, this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        try {
            return Optional.ofNullable(
                    jdbc.queryForObject(SELECT_ALL + " WHERE code = ?", this::mapRowToHardware, code)
            );
        } catch (EmptyResultDataAccessException ex){
            return Optional.empty();
        }
    }

    @Override
    public Set<Hardware> findByString(String text){
        return Set.copyOf(jdbc.query(SELECT_ALL + " WHERE UPPER(name) LIKE UPPER('%" + text + "%')", this::mapRowToHardware));
    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        try {
            hardware.setCode(saveHardwareDetails(hardware));
            return Optional.of(hardware);
        } catch (DuplicateKeyException ex){
            return Optional.empty();
        }
    }

    private String saveHardwareDetails(Hardware hardware){
        Map<String, Object> values = new HashMap<>();

        values.put("code", hardware.getCode());
        values.put("name", hardware.getName());
        values.put("price", hardware.getPrice());
        values.put("type", hardware.getType());
        values.put("amountInStorage", hardware.getAmountInStorage());

        inserter.execute(values);

        return hardware.getCode();
    }

    @Override
    public Optional<Hardware> update(String code, Hardware hardware) {
        int executed = jdbc.update("UPDATE hardware SET " +
                "name = ?," +
                "price = ?," +
                "type = ?,"+
                "amountInStorage = ? "+
                " WHERE code = ?",
                hardware.getName(),
                hardware.getPrice(),
                hardware.getType(),
                hardware.getAmountInStorage(),
                hardware.getCode());

        if(executed > 0){
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {

        jdbc.update("DELETE FROM hardware WHERE code = ?", code);
    }

    private Hardware mapRowToHardware(ResultSet rs, int rowNum) throws SQLException {
        return new Hardware(
                rs.getString("code"),
                rs.getString("name"),
                rs.getFloat("price"),
                HardwareType.valueOf(rs.getString("type")),
                rs.getInt("amountInStorage")
        );
    }

}
