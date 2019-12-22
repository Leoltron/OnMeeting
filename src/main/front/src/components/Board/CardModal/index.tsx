import React, {useState} from 'react';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { MuiPickersUtilsProvider } from '@material-ui/pickers';
import MomentUtils from '@date-io/moment';
import { DatePicker } from "@material-ui/pickers";
import Dialog from '@material-ui/core/Dialog';
import DialogActions from '@material-ui/core/DialogActions';
import DialogContent from '@material-ui/core/DialogContent';
import DialogTitle from '@material-ui/core/DialogTitle';
import {CardViewModel} from "../../../models/CardViewModel";
import {addCard, editCard} from "../../../httpClient";
import {CardAddOrEditModel} from "../../../models/CardAddOrEditModel";
import report from "../../../utils/report";
import {Moment} from "moment";

interface Props {
    card?: CardViewModel
    isOpen: boolean
    close: () => void
}
const CardModal: React.FC<Props> = ({isOpen, close, card}) => {
    const [title, setTitle] = useState(card ? card.title : undefined);
    const [location, setLocation] = useState(card ? card.locationString : undefined);
    const [startDate, setStartDate] = useState<string | null>(card && card.startDate ? card.startDate : null);
    const [endDate, setEndDate] = useState<string | null>(card && card.endDate ? card.endDate : null);
    const [participantsIds, setParticipantsIds] = useState(card ? card.participants.map(p => p.id) : []);
    const [tagIds, setTagIds] = useState(card ? card.tags.map(t => t.id) : []);
    const getSnapshot = () => {
        return {
            title,
            locationString: location,
            startDate,
            endDate,
            participantsIds,
            tagIds
        } as CardAddOrEditModel;
    };
    const onStartDateChange = (date: Moment | null) => {
        if (date) {
            setStartDate(date.format())
        }
    };
    const onEndDateChange = (date: Moment | null) => {
        if (date) {
            setEndDate(date.format())
        }
    };
    const onEdit = async () => {
        try {
            if (card) await editCard(getSnapshot(), card.cardId);
            close()
        } catch (e) {
            report(e)
        }
    };
    const onAdd = async () => {
        try {
            await addCard(getSnapshot());
            close()
        } catch (e) {
            report(e)
        }
    };

    return (
        <Dialog open={isOpen} onClose={close} aria-labelledby="form-dialog-title">
            <DialogTitle id="form-dialog-title">
                {card ? "Edit card" : "Add card"}
            </DialogTitle>
            <DialogContent>
                <TextField
                    autoFocus
                    margin="dense"
                    id="title"
                    label="Title"
                    type="text"
                    fullWidth
                    value={title}
                    onChange={e => setTitle(e.target.value)}
                />
                <TextField
                    margin="dense"
                    id="location"
                    label="Location"
                    type="text"
                    fullWidth
                    value={location}
                    onChange={e => setLocation(e.target.value)}
                />
                <MuiPickersUtilsProvider utils={MomentUtils}>
                    <DatePicker
                        autoOk
                        label="Start date"
                        clearable
                        value={startDate}
                        onChange={onStartDateChange}
                    />
                    <DatePicker
                        autoOk
                        label="End date"
                        clearable
                        value={endDate}
                        onChange={onEndDateChange}
                    />
                </MuiPickersUtilsProvider>
            </DialogContent>
            <DialogActions>
                <Button onClick={close} color="primary">
                    Cancel
                </Button>
                <Button onClick={card ? onEdit : onAdd} color="primary">
                    {card ? "Edit" : "Add"}
                </Button>
            </DialogActions>
        </Dialog>
    );
};

export default CardModal
