package learn.wingit.data;

import learn.wingit.models.PlaneModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ModelRepository {

    List<PlaneModel> findAll();

    PlaneModel findById(int modelId);

    PlaneModel add(PlaneModel model);

    boolean update(PlaneModel model);

    @Transactional
    boolean delete(int modelId);
}
