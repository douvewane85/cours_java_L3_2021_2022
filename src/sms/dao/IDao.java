/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sms.dao;

import java.util.List;

/**
 *
 * @author USER
 */
public interface IDao<T> {
    public int insert(T obj);
    public int update(T obj);
    public T findById(int id);
    public List<T> findAll();
}
