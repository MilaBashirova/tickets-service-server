package ru.innopolis.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.innopolis.dto.UserDto;
import ru.innopolis.dto.UserRoleDto;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "users", schema = "public", catalog = "postgres")
public class UsersEntity {
    @Id
    private long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String password;
    private String email;
    private String phone;
    private Long cityId;
    private Collection<UserRoleEntity> userRolesById;
    private CityEntity City;


    private Set<RoleEntity> roles;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "first_name", nullable = true, length = 100)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "middle_name", nullable = true, length = 100)
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 32)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "city_id", nullable = true,updatable = false,insertable = false)
    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersEntity that = (UsersEntity) o;
        return id == that.id &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(middleName, that.middleName) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone) &&
                Objects.equals(cityId, that.cityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, middleName, password, email, phone, cityId);
    }

    @OneToMany(mappedBy = "usersByUserId")
    public Collection<UserRoleEntity> getUserRolesById() {
        return userRolesById;
    }

    public void setUserRolesById(Collection<UserRoleEntity> userRolesById) {
        this.userRolesById = userRolesById;
    }

    @JoinTable(	name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    @ManyToMany(cascade = {CascadeType.ALL})
    public Set<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleEntity> roles) {
        this.roles = roles;
    }

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id")
    public CityEntity getCity() {
        return City;
    }

    public void setCity(CityEntity city) {
        this.City = city;
    }

    public static UsersEntity fromUserDto(UserDto userDto) {
        CityEntity cityEntity =new CityEntity(userDto.getCity().getId(), userDto.getCity().getCity(),userDto.getCity().getTimeZone() );
        return   UsersEntity.builder().id(userDto.getId())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .middleName(userDto.getMiddleName())
                .password(userDto.getPassword())
                .email(userDto.getEmail())
                .phone(userDto.getPhone())
                .cityId(userDto.getCity().getId())
                //.City(cityEntity)
                .build();
    }
}
