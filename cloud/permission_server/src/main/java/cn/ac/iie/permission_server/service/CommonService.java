package cn.ac.iie.permission_server.service;

import cn.ac.iie.permission_server.repository.CommonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Service
public abstract class CommonService<E, ID extends Serializable> {

    CommonRepository<E, ID> commonRepository;

    public CommonRepository<E, ID> getCommonRepository() {
        return commonRepository;
    }

    public void setCommonRepository(CommonRepository<E, ID> commonRepository) {
        this.commonRepository = commonRepository;
    }

    /**
     * 根据ID获取某个Entity
     * @param id
     * @return
     */
    public E get(ID id) {
        return commonRepository.getOne(id);
    }

    /**
     * 根据ID查找某个Entity（建议使用）
     * @param id
     * @return
     */
    public E find(ID id) {
        return commonRepository.findById(id).orElse(null);
    }

    /**
     * 获取所有的Entity列表
     * @return
     */
    public List<E> getAll() {
        return commonRepository.findAll();
    }

    /**
     * 获取Entity的总数
     * @return
     */
    public Long getTotalCount() {
        return commonRepository.count();
    }

    /**
     * 保存Entity
     * @param entity
     * @return
     */
    public E save(E entity) {
        return commonRepository.save(entity);
    }

    /**
     * 修改Entity
     * @param entity
     * @return
     */
    public E update(E entity) {
        return commonRepository.saveAndFlush(entity);
    }

    /**
     * 删除Entity
     * @param entity
     */
    public void delete(E entity) {
        commonRepository.delete(entity);
    }

    /**
     * 根据Id删除某个Entity
     * @param id
     */
    public void delete(ID id) {
        commonRepository.deleteById(id);
    }

    /**
     * 删除Entity的集合类
     * @param entities
     */
    public void delete(Collection<E> entities) {
        commonRepository.deleteAll(entities);
    }

    /**
     * 清空缓存，提交持久化
     */
    public void flush() {
        commonRepository.flush();
    }

    /**
     * 根据查询信息获取某个Entity的列表
     * @param spec
     * @return
     */
    public List<E> findAll(Example<E> spec) {
        return commonRepository.findAll(spec);
    }

    /**
     * 获取Entity的分页信息
     * @param pageable
     * @return
     */
    public Page<E> findAll(Pageable pageable){
        return commonRepository.findAll(pageable);
    }

    /**
     * 根据查询条件和分页信息获取某个结果的分页信息
     * @param spec
     * @param pageable
     * @return
     */
    public Page<E> findAll(Example<E> spec, Pageable pageable) {
        return commonRepository.findAll(spec, pageable);
    }

    /**
     * 根据查询条件和排序条件获取某个结果集列表
     * @param spec
     * @param sort
     * @return
     */
    public List<E> findAll(Example<E> spec, Sort sort) {
        return commonRepository.findAll(spec);
    }

    /**
     * 查询某个条件的结果数集
     * @param spec
     * @return
     */
    public long count(Example<E> spec) {
        return commonRepository.count(spec);
    }
}
