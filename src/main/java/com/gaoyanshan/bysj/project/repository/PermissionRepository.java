package com.gaoyanshan.bysj.project.repository;

import com.gaoyanshan.bysj.project.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * <pre>类名: PermissionRepository</pre>
 * <pre>描述: Permission 的dao层</pre>
 * <pre>日期: 19-3-16 下午7:59</pre>
 * <pre>作者: gaoyanshan</pre>
 */

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer>{
}
