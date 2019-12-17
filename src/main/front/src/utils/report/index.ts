const report = (error: Error) => {
    console.exception(error.message)
};

export default report;