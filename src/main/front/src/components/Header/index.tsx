import React from "react";
import "./Header.less";
import android from './android-48px.svg';

const Header: React.FC = () => (
    <div className="header">
        <a href="/on-meeting.apk" className="download-app-link"><img src={android}/></a>
        <h1>OnMeeting</h1>
    </div>
);

export default Header;