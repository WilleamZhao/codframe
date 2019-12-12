package com.tlkj.cod.config.service.impl;

import com.tlkj.cod.config.model.enums.CodConfigSourceType;
import com.tlkj.cod.config.service.CodConfigService;
import com.tlkj.cod.config.service.CodConfigChangeListener;
import com.tlkj.cod.config.spring.property.PlaceholderHelper;
import com.tlkj.cod.dao.jdbc.Finder;
import com.tlkj.cod.dao.jdbc.Updater;
import com.tlkj.cod.dao.model.enums.CodDaoDatasourceTypeEnum;
import com.tlkj.cod.dao.util.CodDaoConnectionPool;
import com.tlkj.cod.data.model.entity.CodDataConfigDo;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Desc CodData 数据源
 *
 * @author sourcod
 * @version 1.0
 * @className CodConfigDataServiceImpl
 * @date 2019/5/30 11:17 AM
 */
@Service("codConfigDataServiceImpl")
public class CodConfigDataServiceImpl implements CodConfigService {

    private Map<String, Object> map = new HashMap<>();

    private final PlaceholderHelper placeholderHelper = PlaceholderHelper.getInstance();

    private Finder finder;
    private Updater updater;

    @Override
    public CodConfigSourceType getType() {
        return CodConfigSourceType.DATA;
    }

    @Override
    public boolean init() {
        if (finder == null || updater == null){
            DataSource dataSource = CodDaoConnectionPool.getInstance().getDataSource(CodDaoDatasourceTypeEnum.DATA.name());
            finder = new Finder(dataSource);
            updater = new Updater(dataSource);
        }
        return true;
    }

    @Override
    public Map<String, Object> load() {
        if (finder == null){
            init();
        }
        List<CodDataConfigDo> dataConfigDos = finder.from(CodDataConfigDo.TABLE_NAME).all(CodDataConfigDo.class);
        map = dataConfigDos.stream().collect(Collectors.toMap(CodDataConfigDo::getC_key, CodDataConfigDo::getC_value));
        return map;
    }


    @Override
    public Set<String> list() {
        return map.keySet();
    }

    @Override
    public String getProperty(String key, String defaultValue) {
        if (map.isEmpty()){
            map = load();
        }

        String value = map.getOrDefault(key, defaultValue) != null ? map.getOrDefault(key, defaultValue).toString() : null;

        return value;
    }

    @Override
    public Set<String> getPropertyNames() {
        return null;
    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener) {

    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys) {

    }

    @Override
    public void addChangeListener(CodConfigChangeListener listener, Set<String> interestedKeys, Set<String> interestedKeyPrefixes) {

    }

    @Override
    public boolean removeChangeListener(CodConfigChangeListener listener) {
        return false;
    }

    @Override
    public <T> T getProperty(String key, Function<String, T> function, T defaultValue) {
        return null;
    }

    @Override
    public CodConfigService getSourceType() {
        return null;
    }
}
