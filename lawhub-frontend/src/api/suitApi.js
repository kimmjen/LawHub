import axiosInstance from './axios';

// 사건 목록 조회 API
export const fetchSuits = async () => {
    const response = await axiosInstance.get('/suits');
    return response.data;
};

// 특정 사건 조회 API
export const getSuitById = async (suitId) => {
    const response = await axiosInstance.get(`/suits/${suitId}`);
    return response.data;
};