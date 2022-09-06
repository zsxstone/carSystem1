package com.jkxy.car.api.controller;

import com.jkxy.car.api.pojo.Car;
import com.jkxy.car.api.service.CarService;
import com.jkxy.car.api.utils.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("car")
public class CarController {
    @Autowired
    private CarService carService;

    /**
     * 查询所有
     *
     * @return
     */
    @GetMapping("findAll")
    public JSONResult findAll() {
        List<Car> cars = carService.findAll();
        return JSONResult.ok(cars);
    }

    /**
     * 通过id查询
     *
     * @param id
     * @return
     */
    @GetMapping("findById/{id}")
    public JSONResult findById(@PathVariable int id) {
        Car car = carService.findById(id);
        return JSONResult.ok(car);
    }

    /**
     * 通过车名查询
     *
     * @param carName
     * @return
     */
    @GetMapping("findByCarName/{carName}")
    public JSONResult findByCarName(@PathVariable String carName) {
        List<Car> cars = carService.findByCarName(carName);
        return JSONResult.ok(cars);
    }

    /**
     * 通过id删除
     *
     * @param id
     * @return
     */
    @GetMapping("deleteById/{id}")
    public JSONResult deleteById(@PathVariable int id) {
        carService.deleteById(id);
        return JSONResult.ok();
    }

    /**
     * 通过id更新全部信息
     *
     * @return
     */
    @PostMapping("updateById")
    public JSONResult updateById(Car car) {
        carService.updateById(car);
        return JSONResult.ok();
    }

    /**
     * 通过id增加
     *
     * @param car
     * @return
     */
    @PostMapping("insertCar")
    public JSONResult insertCar(Car car) {
        carService.insertCar(car);
        return JSONResult.ok();
    }
    /**
     * 车辆购买
     *
     * @param carName
     * @param carCount
     * @return
     */
    @GetMapping("buyCar/{carName}/{carCount}")
    public JSONResult buyCar(@PathVariable String carName, @PathVariable int carCount) {
        List<Car> cars = carService.findByCarName(carName);
        Car car;
        if (cars.size() > 0) {
            car = cars.get(0);
            if (car.getCarCount() >= carCount) {
                car.setCarCount(car.getCarCount() - carCount);
                carService.updateById(car);
            } else {
                return JSONResult.errorMsg("库存不足！请重新输入!");
            }
        } else {
            return JSONResult.errorMsg("输入车名错误！请重新输入!");
        }
        return JSONResult.ok("成功购买" + carName + " 数量：" + carCount + "台！");
    }

    /**
     * 模糊查询并分页
     *
     * @param carName
     * @param beginIndex
     * @param endIndex
     * @return
     */
    @GetMapping("findByBeginEndIndex/{carName}/{beginIndex}/{endIndex}")
    public JSONResult buyCar(@PathVariable String carName, @PathVariable int beginIndex, @PathVariable int endIndex) {
        List<Car> cars = carService.findByBeginEndIndex(carName, beginIndex, endIndex);
        return JSONResult.ok(cars);
    }
}
