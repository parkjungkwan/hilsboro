import React, {useState} from 'react';
import 'webapp/artist/style/ArtistSignin.css';
import {Link, useHistory} from 'react-router-dom';
import {useDispatch} from 'react-redux';
import {signinPage} from 'webapp/artist/reducer/artist.reducer';

const Signin = () => {
    const [signin, setSignin] = useState({
        username: '',
        password: '',
    });

    const history = useHistory();
    const dispatch = useDispatch();
    const goSignin = (e) => {
        e.preventDefault();
        e.stopPropagation();
        dispatch(signinPage(signin));
        alert(JSON.stringify(signin.username) + "님 환영합니다")
        history.push('/');
    };

    const handleChange = (e) => {
        const {name, value} = e.target;
        setSignin({
            ...signin,
            [name]: value,
        });
    };

    const cancelButton = (e) => {
        e.preventDefault();
        window.location = '/';
    };

    return (
        <>
            <section className="white-bg">
                <div className="text-center" style={{marginTop: "auto",marginBottom: "240px"}}>
                    <h2>Philo_Arte에 오신걸 환영합니다(Login)</h2>
                </div>

                <div className="container">
                    <label className="pull-right">
                        <Link to="/artist/artist-signup">
                            <button className="btn btn-info btn-md btn-default "> 회원가입</button>
                        </Link>
                    </label>
                    <label htmlFor="username">
                        <b>ID</b>
                    </label>
                    <input type="text" style={{color: "black"}} placeholder="Enter Username" name="username"
                           value={signin.username || ''} onChange={handleChange}/>

                    <label htmlFor="password">
                        <b>비밀번호</b>
                    </label>
                    <input type="password" style={{color: "black"}} placeholder="Enter Password" name="password"
                           value={signin.password || ''} onChange={handleChange}/>

                    <button type="submit" className="btn btn-success btn-md btn-default remove-margin pull-right"
                            onClick={(e) => goSignin(e)}>
                        Login
                    </button>

                    <button type="button" className="btn btn-color btn-md btn-default remove-margin"
                            onClick={cancelButton}>
                        Cancel
                    </button>

                </div>

            </section>
        </>
    );
};
export default Signin;