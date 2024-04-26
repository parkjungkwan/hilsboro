package hillsboro.philoarte.scalar.providerImpls;

import com.fasterxml.jackson.annotation.JsonIgnore;
import hillsboro.philoarte.scalar.entities.Artist;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public class UserProviderImpl implements UserDetails { // UserDetails 은 security 내장형

    private final long artistid;
    private final String username;
    @JsonIgnore
    private final String password;
    private final String artistName;
    private final String email;
    private final String phoneNumber;
    private final String address;
    private final String school;
    private final String department;
    private final Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static UserProviderImpl build(Artist artist) {
        List<GrantedAuthority> authories = artist.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getAuthority())).collect(Collectors.toList());
        return new UserProviderImpl(artist.getArtistId(), artist.getUsername(), artist.getPassword(), artist.getName(),
                artist.getEmail(), artist.getPhoneNumber(), artist.getAddress(), artist.getSchool(),
                artist.getDepartment(), authories);
    }
}
