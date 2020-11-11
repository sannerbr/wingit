package learn.wingit.data;

import learn.wingit.models.Plane;

import java.util.List;

public interface PlaneRepository {

    List<Plane> findAll();

    List<Plane> findByType(int type_id);

    Plane findById(int plane_id);

    Plane add(Plane plane);

    boolean update(Plane plane);

    boolean delete(Plane plane);

}
