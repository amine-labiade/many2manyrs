package com.apptap.freezeos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;


public class TestSystem {
	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("freezeos");
	
	public static void main(String[] args) {
		addCar(1, "Fast","3A25" );
		addCar(2, "Slow","8B25" );
		addCar(3, "Haha","3C67" );
		addCar(4, "Hihi","2D35" );
		getCar(1);
		getCars();
		updateCar(3, "Junk", "23Z666");
		deleteCar(4);
		getCars();
		entityManagerFactory.close();
	}
	
	public static void addCar(int id,String model,String plate) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			Car car = new Car();
			car.setId(id);
			car.setModel(model);
			car.setPlate(plate);
			entityManager.persist(car);
		}catch (Exception ex) {
			if(entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}finally {
			entityManager.close();
		}
	}
	
	public static void getCar(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT c FROM Car c WHERE c.id = :carID";
		
		TypedQuery<Car> tq = entityManager.createQuery(query,Car.class);
		tq.setParameter("carID", id);
		Car car = null;
		try {
			car = tq.getSingleResult();
			System.out.println(car.getModel()+ " " + car.getPlate());
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
	}
	
	public static void getCars() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		String query = "SELECT c FROM Car c WHERE c.id IS NOT NULL";
		TypedQuery<Car> tq = entityManager.createQuery(query,Car.class);
		List<Car> cars;
		try {
			cars = tq.getResultList();
			cars.forEach(car->System.out.println(car.getModel() + " " + car.getPlate()));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			entityManager.close();
		}
	}
	
	public static void updateCar(int id,String model,String plate) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = null;
		Car car = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			car = entityManager.find(Car.class, id);
			car.setModel(model);
			car.setPlate(plate);
			entityManager.persist(car);
			entityTransaction.commit();
		}catch (Exception ex) {
			if(entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}finally {
			entityManager.close();
		}
	}
	
	public static void deleteCar(int id) {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = null;
		Car car = null;
		try {
			entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			car = entityManager.find(Car.class, id);
			entityManager.remove(car);
			entityTransaction.commit();
		}catch (Exception ex) {
			if(entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}finally {
			entityManager.close();
		}
	}
	
}
