import React from 'react'

import {Link} from 'react-router-dom';

const ResumeList = () => {

    return (
        <> < h1 > ResumeList</h1>
        < Link to = "/resume/resume-register" > <button>Resume 등록</button>
    </Link>
    < Link to = "/resume/resume-read" > <button>Resume 읽기</button>
    </Link>
    < Link to = "/resume/resume-modify" > <button>Resume 수정</button>
    </Link>
         < Link to = "/" > <button>취소</button>
    </Link>
</>
    )
}

export default ResumeList