import React from "react";
import { Link } from "react-router-dom";
import LoadScript from "webapp/common/helpers/LoadScript";
import dataGallery from "../data/Gallery/footer-gallery.json";
import logo from "webapp/images/logo-footer.png";
import FooterGallery from "../Gallery/FooterGallery";
import Icofont from "react-icofont";

const Footer = ({ bg }) => {
  LoadScript("./js/master.js");
  return (
    <>
      <footer className="footer">
        <div
          className="footer-main"
          style={{
            background: bg
              ? `url(${bg}) center center no-repeat #24182e`
              : null,
          }}
        >
          <div className="container">
            <div className="row">
              <div className="col-sm-6 col-md-3">
                <div className="widget widget-text">
                  <div className="logo-footer">
                    <Link to={`${process.env.PUBLIC_URL}/`}>
                      <img className="img-responsive" src={logo} alt="" />
                    </Link>
                  </div>
                  <p>
                 Philo Arte에서 여러분들의 재능을 맘껏 보여주세요
                  </p>
                  <p className="mt-20 mb-0">
                    <a href="tel:+44 1234-567-8">+82 02-1234-5678</a>
                  </p>
                  <p className="mt-0">
                    <a
                      href="https://www.google.com/maps/search/Potsdamer+Platz+9797/@52.5096488,13.3737554,17z/data=!3m1!4b1"
                      target="_blank"
                      rel="noopener noreferrer"
                    >
                      서울시 강남구 강남대로 
                    </a>
                  </p>
                </div>
              </div>
              <div className="col-sm-6 col-md-3">
                <div className="widget widget-text widget-links">
                  <h5 className="widget-title">Join The Newsletter</h5>
                  <p>
                  아래 이메일을 입력 후 Philo Arte의 최신 소식을 뉴스레터로 받아보세요
                  </p>
                  <form
                    name="subscribe"
                    id="subscribe"
                    method="POST"
                    className="contact-form-style-02"
                  >
                    <div className="form-group mt-20 mb-0">
                      <label className="sr-only" htmlFor="name">
                        Email
                      </label>
                      <input
                        type="email"
                        name="email"
                        className="md-input style-02"
                        id="email"
                        placeholder="email"
                        required
                        data-error="Your Name is Required"
                      />
                    </div>
                    <div className="text-left mt-10">
                      <button
                        type="submit"
                        name="submit"
                        className="btn btn-lg btn-color btn-square remove-margin"
                      >
                        Subscribe
                      </button>
                    </div>
                  </form>
                </div>
              </div>
              <div className="col-sm-6 col-md-3">
                <div className="widget widget-links">
                  <h5 className="widget-title">Quick Links</h5>
                  <ul>
                    <li>
                      <Link to={"/artist/artist-signup"}>Sign Up</Link>
                    </li>
                    <li>
                      <Link to={"/artist/artist-signin"}>Sign In</Link>
                    </li>
                    <li>
                      <Link to={"/art"}>Art</Link>
                    </li>
                    <li>
                      <Link to={"/resume/home"}>Resume</Link>
                    </li>
                    <li>
                      <Link to={"/funding/list"}>Funding</Link>
                    </li>
                    <li>
                      <Link to={"/reviews/review-list"}>Review </Link>
                    </li>
                    <li>
                      <Link to={"/qnas/qna-list"}>Q&A </Link>
                    </li>
                  </ul>
                </div>
              </div>
              <div className="col-sm-6 col-md-3">
                <div className="widget widget-links">
                  <h5 className="widget-title">Media Gallery</h5>
                  <FooterGallery data={dataGallery} />
                </div>
              </div>
            </div>
          </div>
        </div>
        <div
          className="footer-copyright"
          style={{
            background: bg
              ? `url(${bg}) center center no-repeat #24182e`
              : null,
          }}
        >
          <div className="container">
            <div className="row">
              <div className="col-md-12">
                <div className="copy-right text-center">
                  © 2021 Philo Arte. All rights reserved
                </div>
              </div>
            </div>
          </div>
        </div>
      </footer>
      <a href="!#" id="return-to-top">
        <Icofont icon="arrow-up" />
      </a>
    </>
  );
};

export default Footer;
