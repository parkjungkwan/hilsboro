import React, { useCallback, useEffect, useState } from 'react';
import { useHistory } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { getLocalArtist, mypagePage } from 'webapp/artist/reducer/artist.reducer';
import { ArtistDelete, Logout } from 'webapp/artist/index';

const MyPage = () => {
    const history = useHistory();
    const dispatch = useDispatch();

    const artistsState = useSelector((state) => state.artists.artistsState);
    const artistsFilesimgName = useSelector((state) => state.artists.artistsState.imgName);
    const artistsFilesUuid = useSelector((state) => state.artists.artistsState.uuid);

    const [imgBase64, setImgBase64] = useState('');
    const [files, setFiles] = useState(null);

    const handleUploadFile = (e) => {
        let reader = new FileReader();

        reader.onloadend = () => {
            const base64 = reader.result;
            if (base64) {
                setImgBase64(base64.toString()); 
            }
        };
        if (e.target.files[0]) {
            reader.readAsDataURL(e.target.files[0]); 
            setFiles(e.target.files[0]); 
        }
    };

    const [mypage, setMypage] = useState({
        artistId: artistsState.artistId,
        username: artistsState.usename,
        password: '',
        name: '',
        phoneNumber: '',
        email: '',
        address: '',
        school: '',
        department: '',
        files: artistsState.files,
        token: artistsState.token,
        uuid: artistsState.uuid,
        imgName: artistsState.imgName,
    });


    useEffect(() => {
        dispatch(getLocalArtist());
    }, []);

    const goMypage = async (e) => {
        let mypageResult = window.confirm('Mypage를 수정하시겠습니까?');
        e.preventDefault();
        e.stopPropagation();
        const obj = {
            uuid: artistsState.uuid,
            imgName: artistsState.imgName,
            files: artistsState.files,
            token: artistsState.token,
            artistId: artistsState.artistId,
            username: artistsState.username,
            password: mypage.password,
            name: artistsState.name,
            phoneNumber: mypage.phoneNumber,
            email: mypage.email,
            address: mypage.address,
            school: mypage.school,
            department: mypage.department,
        };

        const formData = new FormData();
        for (let i = 0; i < files.length; i++) {
            formData.append('files[' + i + ']', files[i]);
        }
        formData.append('username', artistsState.username);
        formData.append('artistId', artistsState.artistId);
        formData.append('password', mypage.password);
        formData.append('name', artistsState.name);
        formData.append('email', mypage.email);
        formData.append('phoneNumber', mypage.phoneNumber);
        formData.append('address', mypage.address);
        formData.append('school', mypage.school);
        formData.append('department', mypage.department);
        formData.append('uuid', artistsState.uuid);
        formData.append('imgName', artistsState.imgName);

        if (mypageResult) {
            alert('수정 완료');
            await dispatch(mypagePage(obj));
        }
    };

    const goHome = (e) => {
        e.preventDefault();
        e.stopPropagation();
        history.push('/');
    };

    const handleChange = useCallback(
        (e) => {
            e.preventDefault();
            const { name, value } = e.target;
            setMypage({
                ...mypage,
                [name]: value,
            });
        },
        [mypage]
    );

    const handleChangeFile = (e) => {
        const fileObj = e.target;
        console.dir(fileObj.files);
        setFiles(fileObj.files);
    };

    return (
        <>
            <form>
                <div className="container">
                    <h1>마이 페이지</h1>
                    <hr />

                    <form>
                        <label htmlFor="artistFile">
                            <b>대표이미지</b>
                        </label>
                        <td>
                            <div>
                                <img src={'http://localhost:8080/artist_files/display?imgName=' + `${artistsFilesUuid}` + 's_' + `${artistsFilesimgName}`} />
                                <br />
                                <br />
                                <br />
                            </div>
                        </td>

                        <label htmlFor="artistId">
                            <b>아이디번호</b>
                        </label>
                        <td>{artistsState.artistId} </td>

                        <label htmlFor="username">
                            <b>아이디</b>
                        </label>
                        <td>{artistsState.username} </td>

                        <label htmlFor="password">
                            <b>비밀번호</b>
                        </label>
                        <input type="password" placeholder="password" name="password" value={mypage.password} onChange={(e) => handleChange(e)} />

                        <label htmlFor="name">
                            <b>이름</b>
                        </label>
                        <td>{artistsState.name}</td>

                        <label htmlFor="phoneNumber">
                            <b>핸드폰번호</b>
                        </label>
                        <input type="text" placeholder="phoneNumber" name="phoneNumber" value={mypage.phoneNumber} onChange={(e) => handleChange(e)} />

                        <label htmlFor="email">
                            <b>E-mail</b>
                        </label>
                        <input type="text" placeholder="Enter Email" name="email" value={mypage.email} onChange={(e) => handleChange(e)} />

                        <label htmlFor="address">
                            <b>주소</b>
                        </label>
                        <input type="text" placeholder="Enter Addres" name="address" value={mypage.address} onChange={(e) => handleChange(e)} />

                        <label htmlFor="school">
                            <b>학교</b>
                        </label>
                        <input type="text" placeholder="Enter School" name="school" value={mypage.school} onChange={(e) => handleChange(e)} />

                        <label htmlFor="department">
                            <b>학과</b>
                        </label>
                        <input type="text" placeholder="Enter Department" name="department" value={mypage.department} onChange={(e) => handleChange(e)} />

                        <button
                            type="submit"
                            className="updatebtn"
                            onClick={(e) => {
                                goMypage(e);
                            }}
                        >
                            정보 수정
                        </button>
                    </form>

                    <div className="clearfix">
                        <button type="button" className="cancelbtn" onClick={(e) => goHome(e)}>
                            홈으로
                        </button>
                    </div>
                    <br />
                    <br />
                    <Logout />
                    <br />
                    <br />
                    <ArtistDelete />
                </div>
            </form>
        </>
    );
};
export default MyPage;
