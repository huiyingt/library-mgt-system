import React from 'react';

const AboutUs = () => {
  return (
    <div className="page-container">
      <main className="main-content">
        <div className="grid-container">
          {/* About Us Card */}
          <div className="card hover-card">
            <h1 className="search-title">About Us</h1>
            <p className="aboutus-text">
              Welcome to <strong>My Community Library</strong> – a cozy space where people of all ages 
              come together to explore, learn, and share the joy of reading.  
              With a growing collection of up to 15,000 books, you’ll find everything 
              from timeless classics to the latest bestsellers.  
            </p>
            <p className="aboutus-text">
              We’re proud to serve a warm and growing community. Whether you’re a curious child, 
              a busy student, or simply someone who loves 
              stories, our library is here for you.  
            </p>
            <p className="aboutus-text">
              Beyond just borrowing books, our mission is to create a place where friendships grow, 
              ideas spark, and stories come alive. Join us and make the library your second home!  
            </p>
          </div>
        </div>
      </main>
    </div>
  );
};

export default AboutUs;