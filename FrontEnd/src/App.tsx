import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import LoginPage from './pages/LoginPage';
import HomePage from './pages/HomePage';
import ExpensePage from './pages/ExpensePage';
import IncomePage from './pages/IncomePage';

const App: React.FC = () => (
  <Router>
    <Routes>
      <Route path="/login" element={<LoginPage />} />
      <Route path="/home" element={<HomePage />} />
      <Route path="/expenses" element={<ExpensePage />} />
      <Route path="/incomes" element={<IncomePage />} />
      <Route path="/" element={<Navigate to="/login" replace />} />
      <Route path="*" element={<div>404 - Page Not Found</div>} />
    </Routes>
  </Router>
);

export default App;
