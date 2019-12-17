import React from 'react';
import './App.less';
import Header from '../Header';
import Layout from '../Layout';
import Footer from '../Footer';

const App: React.FC = () => (
  <div className="App-root">
    <Header/>
    <section className="App-main">
        <Layout/>
    </section>
    <Footer/>
  </div>
);

export default App;
