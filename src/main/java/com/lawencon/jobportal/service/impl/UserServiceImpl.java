package com.lawencon.jobportal.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.lawencon.jobportal.authentication.helper.SessionHelper;
import com.lawencon.jobportal.authentication.model.UserPrinciple;
import com.lawencon.jobportal.helper.MappingUtil;
import com.lawencon.jobportal.helper.SpecificationHelper;
import com.lawencon.jobportal.helper.ValidationUtil;
import com.lawencon.jobportal.model.request.PagingRequest;
import com.lawencon.jobportal.model.request.user.LoginRequest;
import com.lawencon.jobportal.model.request.user.RegisterUserRequest;
import com.lawencon.jobportal.model.request.user.UpdateUserRequest;
import com.lawencon.jobportal.model.request.userprofile.CreateUserProfileRequest;
import com.lawencon.jobportal.model.response.user.ListUserResponse;
import com.lawencon.jobportal.model.response.user.UserResponse;
import com.lawencon.jobportal.persistence.entity.Role;
import com.lawencon.jobportal.persistence.entity.User;
import com.lawencon.jobportal.persistence.repository.UserRepository;
import com.lawencon.jobportal.service.RoleService;
import com.lawencon.jobportal.service.UserProfileService;
import com.lawencon.jobportal.service.UserService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final RoleService roleService;
    private final UserProfileService profileService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> login(LoginRequest request) {
        Optional<User> user = repository.findByUsername(request.getUsername());
        if (user.isPresent()) {
            if (!passwordEncoder.matches(request.getPassword(), user.get().getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Wrong username or password");
            }
            return user;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Wrong username or password");
        }
    }


    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username)
                    throws UsernameNotFoundException {
                User user = repository.findByUsername(username)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,
                                "User not found"));
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().getCode()));
                return UserPrinciple.builder().user(user).authorities(authorities)
                        .role(user.getRole()).build();
            }
        };
    }

    @Override
    public User getEntityById(String id) {
        ValidationUtil.idIsExist(id, repository, "User");
        return repository.findById(id).get();
    }

    @Override
    public UserResponse getByid(String id) {
        User user = getEntityById(id);
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setIsActive(user.getIsActive());
        response.setRoleId(user.getRole().getId());
        response.setVersion(user.getVersion());
        return response;
    }

    @Override
    public Page<ListUserResponse> getAll(PagingRequest pagingRequest, String inquiry) {
        PageRequest pageRequest =
                PageRequest.of(pagingRequest.getPage(), pagingRequest.getPageSize(),
                        SpecificationHelper.createSort(pagingRequest.getSortBy()));

        Specification<User> spec = Specification.where(null);
        if (inquiry != null && !inquiry.isBlank()) {
            spec = spec.and(SpecificationHelper.inquiryFilter(Arrays.asList("username"), inquiry));
        }

        Page<User> usersResponse = repository.findAll(spec, pageRequest);

        List<ListUserResponse> responses = usersResponse.map(user -> {
            ListUserResponse response = new ListUserResponse();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setRoleName(user.getRole().getName());
            return response;
        }).toList();
        return new PageImpl<>(responses, pageRequest, usersResponse.getTotalElements());

    }

    @Override
    public void create(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setIsActive(true);
        Role role = null;
        if (request.getRoleId() == null) {
            role = roleService.getByCode("CDT");
        } else {
            SessionHelper.validateRole("SA");
            role = roleService.getEntityById(request.getRoleId());
        }
        user.setRole(role);
        user = repository.save(user);
        CreateUserProfileRequest createUserProfileRequest = new CreateUserProfileRequest();
        MappingUtil.map(request, createUserProfileRequest);
        createUserProfileRequest.setUser(user);
        profileService.create(createUserProfileRequest);
    }

    @Override
    public void update(UpdateUserRequest request) {
        User user = getEntityById(request.getId());
        user.setUsername(request.getUsername());
        user.setIsActive(request.getIsActive());
        Role role = roleService.getEntityById(request.getRoleId());
        user.setRole(role);
        repository.save(user);
    }

    @Override
    public void delete(String id) {
        User user = getEntityById(id);
        repository.delete(user);
    }
}

