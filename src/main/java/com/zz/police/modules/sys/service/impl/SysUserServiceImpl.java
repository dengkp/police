package com.zz.police.modules.sys.service.impl;

import com.zz.police.common.constant.RestApiConstant;
import com.zz.police.common.constant.SystemConstant;
import com.zz.police.common.entity.Page;
import com.zz.police.common.entity.Query;
import com.zz.police.common.entity.Result;
import com.zz.police.common.utils.CommonUtils;
import com.zz.police.common.utils.MD5Utils;
import com.zz.police.modules.sys.dao.SysMenuMapper;
import com.zz.police.modules.sys.dao.SysRoleMapper;
import com.zz.police.modules.sys.dao.SysUserMapper;
import com.zz.police.modules.sys.dao.SysUserRoleMapper;
import com.zz.police.modules.sys.dao.SysUserTokenMapper;
import com.zz.police.modules.sys.entity.SysUserEntity;
import com.zz.police.modules.sys.entity.SysUserTokenEntity;
import com.zz.police.modules.sys.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 系统用户
 * @author dengkp
 */
@Service("sysUserService")
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Autowired
	private SysRoleMapper sysRoleMapper;

	@Autowired
	private SysUserRoleMapper sysUserRoleMapper;

	@Autowired
	private SysUserTokenMapper sysUserTokenMapper;

	/**
	 * 分页查询用户列表
	 * @param params
	 * @return
	 */
	@Override
	public Page<SysUserEntity> listUser(Map<String, Object> params) {
		Query form = new Query(params);
		Page<SysUserEntity> page = new Page<>(form);
		sysUserMapper.listForPage(page, form);
		return page;
	}

	/**
	 * 根据用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Override
	public SysUserEntity getByUserName(String username) {
		return sysUserMapper.getByUserName(username);
	}

	/**
	 * 用户所有机构id
	 * @param userId
	 * @return
	 */
	@Override
	public List<Long> listAllOrgId(Long userId) {
		return sysUserMapper.listAllOrgId(userId);
	}

	/**
	 * 新增用户
	 * @param user
	 * @return
	 */
	@Override
	public Result saveUser(SysUserEntity user) {
		user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
		int count = sysUserMapper.save(user);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msg(count);
	}

	/**
	 * 根据id查询用户
	 * @param userId
	 * @return
	 */
	@Override
	public Result getUserById(Long userId) {
		SysUserEntity user = sysUserMapper.getObjectById(userId);
		user.setRoleIdList(sysUserRoleMapper.listUserRoleId(userId));
		return CommonUtils.msg(user);
	}

	/**
	 * 修改用户
	 * @param user
	 * @return
	 */
	@Override
	public Result updateUser(SysUserEntity user) {
		int count = sysUserMapper.update(user);
		Long userId = user.getUserId();
		sysUserRoleMapper.remove(userId);
		Query query = new Query();
		query.put("userId", userId);
		query.put("roleIdList", user.getRoleIdList());
		sysUserRoleMapper.save(query);
		return CommonUtils.msg(count);
	}

	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	@Override
	public Result batchRemove(Long[] id) {
		int count = sysUserMapper.batchRemove(id);
		sysUserRoleMapper.batchRemoveByUserId(id);
		return CommonUtils.msg(count);
	}

	/**
	 * 查询用户权限集合
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> listUserPerms(Long userId) {
		List<String> perms = sysMenuMapper.listUserPerms(userId);
		Set<String> permsSet = new HashSet<>();
		for(String perm : perms) {
			if(StringUtils.isNotBlank(perm)) {
				permsSet.addAll(Arrays.asList(perm.trim().split(",")));
			}
		}
		return permsSet;
	}

	/**
	 * 查询用户角色集合
	 * @param userId
	 * @return
	 */
	@Override
	public Set<String> listUserRoles(Long userId) {
		List<String> roles = sysRoleMapper.listUserRoles(userId);
		Set<String> rolesSet = new HashSet<>();
		for(String role : roles) {
			if(StringUtils.isNotBlank(role)) {
				rolesSet.addAll(Arrays.asList(role.trim().split(",")));
			}
		}
		return rolesSet;
	}

	/**
	 * 用户修改密码
	 * @param user
	 * @return
	 */
	@Override
	public Result updatePswdByUser(SysUserEntity user) {
		String username = user.getUsername();
		String pswd = user.getPassword();
		String newPswd = user.getEmail();
		pswd = MD5Utils.encrypt(username, pswd);
		newPswd = MD5Utils.encrypt(username, newPswd);
		Query query = new Query();
		query.put("userId", user.getUserId());
		query.put("pswd", pswd);
		query.put("newPswd", newPswd);
		int count = sysUserMapper.updatePswdByUser(query);
		if(!CommonUtils.isIntThanZero(count)) {
			return Result.error("原密码错误");
		}
		return CommonUtils.msg(count);
	}

	/**
	 * 启用用户
	 * @param id
	 * @return
	 */
	@Override
	public Result updateUserEnable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.ENABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msg(id, count);
	}

	/**
	 * 禁用用户
	 * @param id
	 * @return
	 */
	@Override
	public Result updateUserDisable(Long[] id) {
		Query query = new Query();
		query.put("status", SystemConstant.StatusType.DISABLE.getValue());
		query.put("id", id);
		int count = sysUserMapper.updateUserStatus(query);
		return CommonUtils.msg(id, count);
	}

	/**
	 * 重置用户密码
	 * @param user
	 * @return
	 */
	@Override
	public Result updatePswd(SysUserEntity user) {
		SysUserEntity currUser = sysUserMapper.getObjectById(user.getUserId());
		user.setPassword(MD5Utils.encrypt(currUser.getUsername(), user.getPassword()));
		int count = sysUserMapper.updatePswd(user);
		return CommonUtils.msg(count);
	}

	/**
	 * 保存用户token
	 * @param userId
	 * @return
	 */
	@Override
	public int saveOrUpdateToken(Long userId, String token) {
		Date now = new Date();
		Date expire = new Date(now.getTime() + RestApiConstant.TOKEN_EXPIRE);
		SysUserTokenEntity sysUserTokenEntity = new SysUserTokenEntity();
		sysUserTokenEntity.setUserId(userId);
		sysUserTokenEntity.setGmtModified(now);
		sysUserTokenEntity.setGmtExpire(expire);
		sysUserTokenEntity.setToken(token);
		int count = sysUserTokenMapper.update(sysUserTokenEntity);
		if (count == 0) {
			return sysUserTokenMapper.save(sysUserTokenEntity);
		}
		return count;
	}

	/**
	 * 根据token查询
	 * @param token
	 * @return
	 */
	@Override
	public SysUserTokenEntity getUserTokenByToken(String token) {
		return sysUserTokenMapper.getByToken(token);
	}

	/**
	 * 根据userId查询
	 * @param userId
	 * @return
	 */
	@Override
	public SysUserTokenEntity getUserTokenByUserId(Long userId) {
		return sysUserTokenMapper.getByUserId(userId);
	}

	/**
	 * 根据userId查询：用于token校验
	 * @param userId
	 * @return
	 */
	public SysUserEntity getUserByIdForToken(Long userId) {
		return sysUserMapper.getObjectById(userId);
	}

}
