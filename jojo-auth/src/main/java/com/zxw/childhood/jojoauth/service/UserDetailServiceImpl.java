package com.zxw.childhood.jojoauth.service;

import com.zxw.childhood.jojoauth.dao.TUserDao;
import com.zxw.childhood.jojoauth.model.pojo.TRole;
import com.zxw.childhood.jojoauth.model.pojo.TUser;
import com.zxw.childhood.jojoauth.model.vo.TUserVO;
import com.zxw.childhood.jojoauth.utils.UserDetailsImpl;
import lombok.extern.java.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zxw
 * @date 2020-05-30 16:57
 */
@Service("userDetailServiceImpl")
public class UserDetailServiceImpl implements UserDetailsService {

    protected final org.apache.commons.logging.Log logger = LogFactory.getLog(getClass());

    @Autowired
    private TUserDao tUserDao;
    /**
         * Locates the user based on the username. In the actual implementation, the search
         * may possibly be case sensitive, or case insensitive depending on how the
         * implementation instance is configured. In this case, the <code>UserDetails</code>
         * object that comes back may have a username that is of a different case than what
         * was actually requested..
         *
         * @param username the username identifying the user whose data is required.
         * @return a fully populated user record (never <code>null</code>)
         * @throws UsernameNotFoundException if the user could not be found or the user has no
         *                                   GrantedAuthority
         */
        @Override
        public UserDetailsImpl loadUserByUsername(String username) throws UsernameNotFoundException {
            List<TUserVO> tUsers = tUserDao.loadUsersByUsername(username);
            if (tUsers.size() == 0) {
                logger.debug("Query returned no results for user '" + username + "'");

                throw new UsernameNotFoundException("Username {0} not found");
            }
            TUserVO tUser = tUsers.get(0);
            String userId =tUser.getUserId();
            List<TRole> roles = tUser.getTRoleList();
            if (roles==null || roles.size()==0){
                this.logger.debug("User '" + username
                        + "' has no authorities and will be treated as 'not found'");

                throw new UsernameNotFoundException(
                        "User  has no GrantedAuthority");
            }

          /*  List<UserDetails> users = loadUsersByUsername(username);

            if (users.size() == 0) {
                log.debug("Query returned no results for user '" + username + "'");

                throw new UsernameNotFoundException(
                        this.messages.getMessage("JdbcDaoImpl.notFound",
                                new Object[] { username }, "Username {0} not found"));
            }

            UserDetails user = users.get(0); // contains no GrantedAuthority[]

            Set<GrantedAuthority> dbAuthsSet = new HashSet<>();

            if (this.enableAuthorities) {
                dbAuthsSet.addAll(loadUserAuthorities(user.getUsername()));
            }

            if (this.enableGroups) {
                dbAuthsSet.addAll(loadGroupAuthorities(user.getUsername()));
            }

            List<GrantedAuthority> dbAuths = new ArrayList<>(dbAuthsSet);

            addCustomAuthorities(user.getUsername(), dbAuths);

            if (dbAuths.size() == 0) {
                this.logger.debug("User '" + username
                        + "' has no authorities and will be treated as 'not found'");

                throw new UsernameNotFoundException(this.messages.getMessage(
                        "JdbcDaoImpl.noAuthority", new Object[] { username },
                        "User {0} has no GrantedAuthority"));
            }

            return createUserDetails(username, user, dbAuths);*/
          return new UserDetailsImpl(tUser);
    }
}
